package com.lt.io;

import com.lt.enums.HttpConstant;
import com.lt.utils.HttpUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author: LT
 * @Date: 2019/11/6 16:23
 * @Description: 参考的博客：
 * https://github.com/jasonGeng88/blog/blob/master/201708/java-nio.md
 * @Version 1.0
 */
public class NioNonBlockingHttpClient {

    private static Selector selector;
    private Charset charset = Charset.forName("utf8");

    /**
     * 由上面分析可以，我们得有一个选择器，
     * 它能监听所有的 I/O 操作，并且以事件的方式通知我们哪些 I/O 已经就绪了。
     */
    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();

        NioNonBlockingHttpClient client = new NioNonBlockingHttpClient();

        for (String host : HttpConstant.HOSTS) {

            client.request(host, HttpConstant.PORT);

        }

        client.select();
        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }

    /**
     * 注意：只有在socketChannel.configureBlocking(false)之后的代码，才是非阻塞的，
     * 如果socketChannel.connect()在设置非阻塞模式之前，那么连接操作依旧是阻塞调用的。
     *
     * @param host
     * @param port
     * @throws IOException
     */
    public void request(String host, int port) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(host, port);
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        socketChannel.connect(remote);
        /*
        选择器与 socket 都创建好了，下一步就是将两者进行关联，好让选择器和监听到 Socket 的变化。
        这里采用了以 SocketChannel 主动注册到选择器的方式进行关联绑定，这也就解释了，
        为什么不直接new Socket()，而是以SocketChannel的方式来创建 socket。
         */
        socketChannel.register(selector,
                SelectionKey.OP_CONNECT
                        | SelectionKey.OP_READ
                        | SelectionKey.OP_WRITE);
    }

    /**
     * 现在，选择器已经与我们关心的 socket 进行了关联。下面就是感知事件的变化，然后调用相应的处理机制。
     * <p>
     * 这里与 Linux 下的 selector 有点不同，nio 下的 selecotr 不会去遍历所有关联的 socket。
     * 我们在注册时设置了我们关心的事件类型，每次从选择器中获取的，只会是那些符合事件类型，
     * 注意：这里的selector.select()是同步阻塞的，等待有事件发生后，才会被唤醒。
     * 这就防止了 CPU 空转的产生。当然，我们也可以给它设置超时时间，
     * selector.select(long timeout)来结束阻塞过程。
     * 并且完成就绪操作的 socket，减少了大量无效的遍历操作
     *
     * @throws IOException
     */
    public void select() throws IOException {
        //// 获取就绪的 socket 个数
        while (selector.select(500) > 0) {
            //// 获取符合的 socket 在选择器中对应的事件句柄 key
            Set keys = selector.selectedKeys();
            //// 遍历所有的key
            Iterator it = keys.iterator();
// 获取对应的 key，并从已选择的集合中移除
            while (it.hasNext()) {

                SelectionKey key = (SelectionKey) it.next();
                it.remove();

                if (key.isConnectable()) {
                    // 进行连接操作
                    connect(key);
                } else if (key.isWritable()) {
                    // 进行写操作
                    write(key);
                } else if (key.isReadable()) {
                    // 进行读操作
                    receive(key);
                }
            }
        }
    }

    /**
     * 一个 socket 是如何来处理连接、写入数据和读取数据的
     * （这些操作都是阻塞的过程，只是我们将等待就绪的过程变成了非阻塞的了）。
     * // SelectionKey 代表 SocketChannel 在选择器中注册的事件句柄
     *
     * @param key
     * @throws IOException
     */
    private void connect(SelectionKey key) throws IOException {
        // 获取事件句柄对应的 SocketChannel
        SocketChannel channel = (SocketChannel) key.channel();
        // 真正的完成 socket 连接
        channel.finishConnect();
        // 打印连接信息
        InetSocketAddress remote = (InetSocketAddress) channel.socket().getRemoteSocketAddress();
        String host = remote.getHostName();
        int port = remote.getPort();
        System.out.println(String.format("访问地址: %s:%s 连接成功!", host, port));
    }

    /**
     * 处理写入就绪事件
     *
     * @param key
     * @throws IOException
     */
    private void write(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        InetSocketAddress remote = (InetSocketAddress) channel.socket().getRemoteSocketAddress();
        String host = remote.getHostName();

        String request = HttpUtil.compositeRequest(host);
        System.out.println(request);
        // 向 SocketChannel 写入事件
        channel.write(charset.encode(request));
        key.interestOps(SelectionKey.OP_READ);
        /*
        这里有两个地方需要注意：

第一个是使用 channel.write(charset.encode(request)); 进行数据写入。有人会说，为什么不能像上面同步阻塞那样，
通过PrintWriter包装类进行操作。因为PrintWriter的 write() 方法是阻塞的，也就是说要等数据真正从 socket 发送出去后才返回。
这与我们这里所讲的阻塞是不一致的，这里的操作虽然也是阻塞的，但它发生的过程是在数据从用户空间到内核缓冲区拷贝过程。
至于系统将缓冲区的数据通过 socket 发送出去，这不在阻塞范围内。也解释了为什么要用 Charset 对写入内容进行编码了，
因为缓冲区接收的格式是ByteBuffer。

第二，选择器用来监听事件变化的两个参数是 interestOps 与 readyOps。

interestOps：表示 SocketChannel 所关心的事件类型，也就是告诉选择器，当有这几种事件发生时，才来通知我。
这里通过key.interestOps(SelectionKey.OP_READ);告诉选择器，之后我只关心“读就绪”事件，其他的不用通知我了。

readyOps：表示 SocketChannel 当前就绪的事件类型。以key.isReadable()为例，判断依据就是：return (readyOps() & OP_READ) != 0;
         */
    }

    /**
     * 处理读取就绪事件
     *
     * @param key
     * @throws IOException
     */
    private void receive(SelectionKey key) throws IOException {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer);
        buffer.flip();
        String receiveData = charset.decode(buffer).toString();
        // 当再没有数据可读时，取消在选择器中的关联，并关闭 socket 连接
        if ("".equals(receiveData)) {
            key.cancel();
            channel.close();
            return;
        }

        System.out.println(receiveData);
        /*
         * 这里的处理基本与写入一致，唯一要注意的是，这里我们需要自行处理去缓冲区读取数据的操作。首先会分配一个固定大小的缓冲区，
         * 然后从内核缓冲区中，拷贝数据至我们刚分配固定缓冲区上。这里存在两种情况：
         *
         * 我们分配的缓冲区过大，那多余的部分以0补充（初始化时，其实会自动补0）。
         * 我们分配的缓冲去过小，因为选择器会不停的遍历。只要 SocketChannel 处理读就绪状态，那下一次会继续读取。当然，分配过小，
         * 会增加遍历次数
         */
    }
}
