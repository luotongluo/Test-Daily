package com.lt.alidemo.service;

import com.lt.alidemo.AlidemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * @Author: LT
 * @Date: 2020/2/27 16:45
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlidemoApplication.class})
public class JokeServiceTest {
    @Autowired
    private JokeService jokeService;

    @Test
    public void getTextJoke() throws Exception {
        HashMap<String, String> reqMap = new HashMap<>();
        //查看文字笑话
        reqMap.put("path", "/xiaohua/text");
        //页码
        reqMap.put("pagenum", "1");
        //每页条数 最大20
        reqMap.put("pagesize", "20");
        //排序 addtime按时间倒叙 rand随机获取 sort=rand时，pagenum无效
        reqMap.put("sort", "addtime");
        this.jokeService.getTextJoke(reqMap);
        /*
        {"status":0,"msg":"ok","result":{"total":79510,"pagenum":1,"pagesize":20,"list":[{"content":"&nbsp; &nbsp; 小区门口修车师傅生意特好。人实在,只要不换零件，常常不收钱或只收个块把钱辛苦钱。最近发现他心黑了，价钱明显上调,就连充个气也收1块钱。旁边书报亭大妈是知情人，: 唉，多担待一下吧！他家上个月二胎,一窝生了四个带把的。。。。","addtime":"2019-06-09 03:20:33","url":"http:\/\/m.kaixinhui.com\/detail-128249.html"},{"content":"前天和同事去逛超市，说好久没买肉吃了，于是去看猪肉，结果看到的最便宜的猪肉都要十八块多，同事当场惊呼：“猪怎么了，这是！”卖肉的师傅笑着说：“猪没事，就是涨价了。”","addtime":"2019-05-04 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128248.html"},{"content":"去小卖部买烟，10块钱一盒的，给老板100，老板喜咪咪的看着我说：“我没零钱找你，就让我闺女陪你一晚抵账得了。”我一激动：“你老别介啊，我攒个私房钱出来偷偷买烟，真的很不容易啊！”老板：“怎么，你不愿意？要不然我去把我闺女叫出来！”我：“别！别！这钱你不用找了，千万别让您闺女知道我藏私房钱啊！”老板：“好女婿，只要你经常来买东西，我保证不说！哈哈哈”","addtime":"2019-05-04 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128247.html"},{"content":"昨天路过单位餐厅门口的金鱼池，正好看到一条小金鱼蹦到外边了，还在张嘴，就捡起来扔到池子里。回到家跟老婆聊天时随口说起，老婆抱怨着说，“我说你个缺心眼的，怎么不去向它要幢别墅呢！”","addtime":"2019-04-12 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128246.html"},{"content":"刚才在QQ上有个还在上大学的姑娘突然跟我讲：“朋友介绍我去打工，今天晚上要到一个酒吧领舞，现在有点不敢去。”我：“有什么不敢去的？”姑娘：“刚才在小摊上只吃了碗牛肉面，怕到时候饿。”──姑娘，你心太宽了！","addtime":"2019-04-12 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128245.html"},{"content":"为什么古装剧里总是有女人会对恩人说：小女子无以为报，唯有以身相许，古代真的存在这种现象吗？ 扯淡，那是因为她喜欢他，要是不喜欢，她就会说：小女子无以为报，唯有来生再报了。","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128244.html"},{"content":"网上聊了一妹子，今天见面。问老妈要了二百块钱，老妈问我干嘛用，我说约会，她高高兴兴的就给了。刚出家门，老爸就把我拽到一边说“小子，二百块钱给我一百，半个月不知道烟的滋味了。”我说“爸，这是我和女朋友的约会钱，不能给你。”老爸说了。“你约什么会，那是我刚建的小号，为了抽颗烟我和你聊了半个月了，快拿来。‘’","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128243.html"},{"content":"家长对孩子的教育真的非常重要。有些男孩，小时候调皮的很，偷了邻居家一根针，家长也不管，一根针嘛，没事。结果孩子长大了，不好好学习每天呆在家里刺十字绣。","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128242.html"},{"content":"一女人问大师：大师，在这么复杂险恶的世界，我一个弱女子如何保护自己？大师说：你把妆卸了！","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128241.html"},{"content":"楼主大四女生，这几天和同一宿舍的女生一起去找工作，在火车站，碰到一个大姐，带着一个四五岁的小男孩侯车，小男孩总是粘着我，要坐我腿上，同宿舍的女生很是羡慕我有人缘，我得意的问小男孩：“你为什么老是喜欢坐姐姐腿上？” 小男孩用稚嫩的口气回答：“姐姐腿上肉多，坐着舒服” 我：“尼玛，这是谁家小哔崽子，快点领走！”","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128240.html"},{"content":"女：“我购物车里的那些水果牛奶饮料零食你赶紧给我买！” 男：“着什么急呀？” 女：“天越来越热了，会放坏的。”","addtime":"2019-04-10 03:20:03","url":"http:\/\/m.kaixinhui.com\/detail-128239.html"},{"content":"朋友问我：有一种鸟，好笑又神经，它是什么鸟？我：我不知道。。沙雕！","addtime":"2019-04-09 03:20:06","url":"http:\/\/m.kaixinhui.com\/detail-128238.html"},{"content":"昨天在家看抗日神剧，我问六岁的小侄女：“宝宝，你知道为什么曰本人头盔两耳边有两块布么？” 她不假思索的瞟了我一眼：“叔叔你真笨，他们设计这两块布肯定是防止被长官打耳光的！” 我。。。","addtime":"2019-04-09 03:20:06","url":"http:\/\/m.kaixinhui.com\/detail-128237.html"},{"content":"昨天哥哥和嫂子有事出门，把小侄子留给我了，我带他去街上，非要拉着我给他买玩具，我给他说：“姑姑兜里没带钱。” 小侄子从自己兜里掏出三百块钱说：“就知道你小气，花我的钱。” 我俩去玩具店，给侄子买了好多玩具，又去吃了顿饭，三百块终于造完了。 摸着肚子问侄子：“这钱是你妈留给你的吗？” 侄子摸摸头说：“不是啊！来的时候趁你不注意，在你包里拿的。”","addtime":"2019-04-09 03:20:06","url":"http:\/\/m.kaixinhui.com\/detail-128236.html"},{"content":"今天不巧看见前女友，更不巧的是我俩都穿的以前买的情侣装。。。 我寻思见面总得打个招呼吧，就贱贱的过去了，本想说“好巧啊，咱俩竟还能然穿情侣装”，没想到被她抢先一句“呦～还有跟老娘穿亲子装的！” 。。。","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128235.html"},{"content":"今天坐高铁回家注意到身边一名男子，他穿得干净、得体，脸庞留下少许岁月痕迹。 他忧郁的眼神，时而静静望窗外，像是思考过往人生；时而双眼微闭，让疲倦的身体有片刻歇息。 根据个人多年行为模式研究、心理学观察及社会经验判断——――这人十有八九是手机没电了。","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128234.html"},{"content":"家里大拜祖先。小明不拜，说：“拜祖先有什么用，祖先又不会保佑的！”爸爸：“你个死孩子，不要乱说！”小明：“谁乱说了啊。”他指着供桌上的猪头：“要是祖先会保佑的话，这猪的祖先天蓬元帅怎么会让自己的后辈子孙给人宰了当祭品。”","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128233.html"},{"content":"三毛去发型屋做发型，对发型师说：给我编个麻花辫。发型师不小心弄掉了三毛的一根头发。三毛叹口气说：那来个中分好拉。可是发型师不小心又弄掉了根。三毛一看火了：你丫的想让我披头散发？","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128232.html"},{"content":"酒吧里，一个大汉侃侃而谈：“我们出来 混社团，最重要的就是信用二字！”旁边 几个小年轻赶紧上前，崇拜的为他点了 一支烟问：“哥，不知您混的哪个社 团？”大汉吸了口烟，眯着眼睛答道：“铁 岭农村信用社。”","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128231.html"},{"content":"问：什么生物拥有超强的臂力、锋利的指甲和尖锐的牙齿，以至于能撕碎一切坚硬牢固的东西？答：拆快递时的妹子。","addtime":"2019-04-09 03:20:04","url":"http:\/\/m.kaixinhui.com\/detail-128230.html"}]}}

         */

    }

    @Test
    public void getxiaohua() throws Exception {
        HashMap<String, String> reqMap = new HashMap<>();
        //查询图片笑话
        reqMap.put("path", "/xiaohua/pic");
        //页码
        reqMap.put("pagenum", "1");
        //每页条数 最大20
        reqMap.put("pagesize", "20");
        //排序 addtime按时间倒叙 rand随机获取 sort=rand时，pagenum无效
        reqMap.put("sort", "addtime");
        this.jokeService.getTextJoke(reqMap);
        /*
        res:
        {"status":0,"msg":"ok","result":{"total":46632,"pagenum":1,"pagesize":20,"list":[{"content":"祖国是母亲 有一天上课，老师问小丽：“祖国是什么？”小丽说：“老师，祖国是我的母亲。”老师说：“回答的很好。”接着老师又问小明：“小明，祖国是什么啊？”小明说：“老师，祖国是小丽的母亲。” 156 100 0 紫由 2019-02-22 + 关注 那是物业 新兵外出拉练，一新兵瞅见路边一个穿制服的肩膀上六颗星，心中一惊：六颗星啊！“啪”的一下一个立正军礼，以示敬意。排长跑过来就是一耳光：“敬你妈的礼，那是物业！” 0 0 0 紫由 2019-02-21 + 关注 一波三折 某人得一宝贝紫砂壶，夜必放床头。一次失手将紫砂壶壶盖打翻，惊醒甚恼，壶盖既碎，留壶身何用?于是抓起壶扔到窗外。天明，发现壶盖掉在棉鞋上，无损。恨之，一脚把壶盖踩得粉碎。出门，见昨晚扔出窗外的茶壶，完好挂在树枝上，坑爹啊! 0 0 1 紫由 2019-02-21 + 关注 明白了 .谢师宴上，某同学对老师说：“好老师，我必须敬您一杯！您对我真好啊，每次讲完题都第一个问我听明白了没有。”老师说：“其实我是觉得，你要是明白了，大家都明白了……” 0 0 0 何大宝 2019-02-21 + 关注 求婚动机 两位已婚男士闲聊，一位提议谈谈各自求婚的动机。“我是在夏天见她穿了一身薄衣服，美腿隐隐若现，于是就向她求婚了。”“我正好相反，”另一位接着道：“我老婆总是穿长裙，我想知道她的腿究竟长得怎么样，于是就向她求婚了！” 0 0 2 何大宝 2019-02-21 + 关注 谁的老婆最瘦 三人吹牛比谁的老婆最瘦。一个说:“我老婆的围巾能当衣服穿。”另一个不服:“我老婆洗澡时不小心能掉进下水道。”第三个镇定地说道:“我老婆吞颗杏仁，别人都以为她怀孕了。” 0 0 0 紫由 2019-02-20 + 关注 要不要再来一次？ 去超市买东西，看到一个小女孩，趴在地上，站在旁边的爸爸，两手提满了东西。 我赶紧跑过去，扶小姑娘起来。旁边的爸爸笑着，跟我说了声谢谢。 我转身还没走远，就听到小女孩，问她爸爸：刚穿裙子的这姐姐，弯腰的时候看到没，要不要再来一次？ 0 0 0 紫由 2019-02-20 + 关注 倒是给我留双筷子啊 家里被小偷光顾，东西被搬空。厨房里也只剩下一碗面和一张纸条，纸条上写道:‘把你家偷光了，觉得挺不好意思。特意为你做了碗面，希望你回来时没凉。 贼字 ’ 我上忍住感动的泪水大喊了一声:你TM倒是给我留双筷子啊 0 0 0 粉色娘子军 2019-02-20 + 关注 挑毛病 丈夫对妻子说，“你不要什么事都挑毛病了，我发现你没有一分钟不挑毛病的。”　　妻子：“那我就一分钟不挑毛病给你看。”一会儿，她脱口而出说：“这房子里热得像地狱一样，你为什么总是把空调器开得那么小呢？”丈夫：“我就知道你不能有一分钟不挑毛病。”“就算是这样。”妻子承认说，“我坚持了多长时间？”丈夫：“仅仅三秒钟。”“三秒钟，去你的吧！”妻子对丈夫吼道，“我没有告诉过你不要买外国表？那些表根本不准！” 0 0 0 粉色娘子军 2019-02-20 + 关注 小事大事 最近看到人家穿针织衫都很好看,想买一件,于是和老公说。我:“老公,我想买一件针织衫。”老公:“想买就买,这么小的事情不用和我商量,我是管大事的。”转头老公想拿个桃吃,发现桃不见了。老公:“那个桃呢?”我:“我吃了。”老公:“这么大的事你都不和我商量一下,我没同意你怎么能吃呢。” 0 0 0 何大宝 2019-02-19 + 关注 小狗：我冤枉啊 &nbsp; &nbsp; &nbsp;邻居很不孝顺，这天他妈来要生活费，又被他骂哭了，大家虽然看不惯，但也没法吭声。刚好我牵着我家的两条狗在邻居门口遛，我家的两条狗是一大一小，母子关系。看着邻居骂骂咧咧，死活不给生活费，突然我狠狠踹了我家的小狗一脚，骂道:“你这个狗东西，自己吃得饱饱的，还抢你妈嘴里的，你让你妈饿着你舒坦是吧？你这个不肖的东西，不办人事，就算老天给你一张人皮你也是畜生。”四周的邻居都来围观我骂狗，都说骂得好，狠狠地骂，我也越骂越顺口，后来我老婆来了，我以为她来劝我回家少惹事，谁知道她默默地递给我一瓶矿泉水 …&nbsp; zylrocket 你就是这样被你邻居暴打一顿的！ 0 0 7 何大宝 2019-02-19 + 关注 很久没洗澡了 丈夫当了个小官，自然少不了应酬。但是妻子不放心，怕在外面乱搞。一日，丈夫回来很晚，原准备了很多解释的词，回来一见妻子已睡，喜出望外，心随之放松了。睡下，妻子挨上来亲热，首先用脚搓了搓丈夫的脚背，问：“怎么脚这么滑呀？”丈夫回答说是今天按摩院洗足浴，没办法，要应酬。一阵子后，妻子抚着丈夫的胸再问：“身上怎么这么滑呀！”丈夫说：“今天 ……”说到半句，突然感觉不对，赶紧改口，“这不关小姐的事，是我很久没洗澡了。 0 0 0 粉色娘子军 2019-02-01 + 关注 孝顺挺好，可泡脚需谨慎呀","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/202001\/25032030_15926.jpg","addtime":"2020-01-25 03:20:30","url":"http:\/\/m.kaixinhui.com\/detail-128182.html"},{"content":"当着你的面我是个高人， 转过身去我也不是菜鸟 0 0 1 采妮 2019-01-28 + 关注 姑娘换衣服就是任性","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201912\/17032129_51719.jpg","addtime":"2019-12-17 03:21:29","url":"http:\/\/m.kaixinhui.com\/detail-127828.html"},{"content":"一波三折 某人得一宝贝紫砂壶，夜必放床头。一次失手将紫砂壶壶盖打翻，惊醒甚恼，壶盖既碎，留壶身何用?于是抓起壶扔到窗外。天明，发现壶盖掉在棉鞋上，无损。恨之，一脚把壶盖踩得粉碎。出门，见昨晚扔出窗外的茶壶，完好挂在树枝上，坑爹啊! 0 0 1 紫由 2019-02-21 + 关注 明白了 .谢师宴上，某同学对老师说：“好老师，我必须敬您一杯！您对我真好啊，每次讲完题都第一个问我听明白了没有。”老师说：“其实我是觉得，你要是明白了，大家都明白了……” 0 0 0 何大宝 2019-02-21 + 关注 求婚动机 两位已婚男士闲聊，一位提议谈谈各自求婚的动机。“我是在夏天见她穿了一身薄衣服，美腿隐隐若现，于是就向她求婚了。”“我正好相反，”另一位接着道：“我老婆总是穿长裙，我想知道她的腿究竟长得怎么样，于是就向她求婚了！” 0 0 2 何大宝 2019-02-21 + 关注 谁的老婆最瘦 三人吹牛比谁的老婆最瘦。一个说:“我老婆的围巾能当衣服穿。”另一个不服:“我老婆洗澡时不小心能掉进下水道。”第三个镇定地说道:“我老婆吞颗杏仁，别人都以为她怀孕了。” 0 0 0 紫由 2019-02-20 + 关注 要不要再来一次？ 去超市买东西，看到一个小女孩，趴在地上，站在旁边的爸爸，两手提满了东西。 我赶紧跑过去，扶小姑娘起来。旁边的爸爸笑着，跟我说了声谢谢。 我转身还没走远，就听到小女孩，问她爸爸：刚穿裙子的这姐姐，弯腰的时候看到没，要不要再来一次？ 0 0 0 紫由 2019-02-20 + 关注 倒是给我留双筷子啊 家里被小偷光顾，东西被搬空。厨房里也只剩下一碗面和一张纸条，纸条上写道:‘把你家偷光了，觉得挺不好意思。特意为你做了碗面，希望你回来时没凉。 贼字 ’ 我上忍住感动的泪水大喊了一声:你TM倒是给我留双筷子啊 0 0 0 粉色娘子军 2019-02-20 + 关注 挑毛病 丈夫对妻子说，“你不要什么事都挑毛病了，我发现你没有一分钟不挑毛病的。”　　妻子：“那我就一分钟不挑毛病给你看。”一会儿，她脱口而出说：“这房子里热得像地狱一样，你为什么总是把空调器开得那么小呢？”丈夫：“我就知道你不能有一分钟不挑毛病。”“就算是这样。”妻子承认说，“我坚持了多长时间？”丈夫：“仅仅三秒钟。”“三秒钟，去你的吧！”妻子对丈夫吼道，“我没有告诉过你不要买外国表？那些表根本不准！” 0 0 0 粉色娘子军 2019-02-20 + 关注 小事大事 最近看到人家穿针织衫都很好看,想买一件,于是和老公说。我:“老公,我想买一件针织衫。”老公:“想买就买,这么小的事情不用和我商量,我是管大事的。”转头老公想拿个桃吃,发现桃不见了。老公:“那个桃呢?”我:“我吃了。”老公:“这么大的事你都不和我商量一下,我没同意你怎么能吃呢。” 0 0 0 何大宝 2019-02-19 + 关注 小狗：我冤枉啊 &nbsp; &nbsp; &nbsp;邻居很不孝顺，这天他妈来要生活费，又被他骂哭了，大家虽然看不惯，但也没法吭声。刚好我牵着我家的两条狗在邻居门口遛，我家的两条狗是一大一小，母子关系。看着邻居骂骂咧咧，死活不给生活费，突然我狠狠踹了我家的小狗一脚，骂道:“你这个狗东西，自己吃得饱饱的，还抢你妈嘴里的，你让你妈饿着你舒坦是吧？你这个不肖的东西，不办人事，就算老天给你一张人皮你也是畜生。”四周的邻居都来围观我骂狗，都说骂得好，狠狠地骂，我也越骂越顺口，后来我老婆来了，我以为她来劝我回家少惹事，谁知道她默默地递给我一瓶矿泉水 …&nbsp; zylrocket 你就是这样被你邻居暴打一顿的！ 0 0 7 何大宝 2019-02-19 + 关注 很久没洗澡了 丈夫当了个小官，自然少不了应酬。但是妻子不放心，怕在外面乱搞。一日，丈夫回来很晚，原准备了很多解释的词，回来一见妻子已睡，喜出望外，心随之放松了。睡下，妻子挨上来亲热，首先用脚搓了搓丈夫的脚背，问：“怎么脚这么滑呀？”丈夫回答说是今天按摩院洗足浴，没办法，要应酬。一阵子后，妻子抚着丈夫的胸再问：“身上怎么这么滑呀！”丈夫说：“今天 ……”说到半句，突然感觉不对，赶紧改口，“这不关小姐的事，是我很久没洗澡了。 0 0 0 粉色娘子军 2019-02-01 + 关注 孝顺挺好，可泡脚需谨慎呀","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201903\/26032021_42795.jpg","addtime":"2019-03-26 03:20:21","url":"http:\/\/m.kaixinhui.com\/detail-128120.html"},{"content":"挑毛病 丈夫对妻子说，“你不要什么事都挑毛病了，我发现你没有一分钟不挑毛病的。”　　妻子：“那我就一分钟不挑毛病给你看。”一会儿，她脱口而出说：“这房子里热得像地狱一样，你为什么总是把空调器开得那么小呢？”丈夫：“我就知道你不能有一分钟不挑毛病。”“就算是这样。”妻子承认说，“我坚持了多长时间？”丈夫：“仅仅三秒钟。”“三秒钟，去你的吧！”妻子对丈夫吼道，“我没有告诉过你不要买外国表？那些表根本不准！” 0 0 0 粉色娘子军 2019-02-20 + 关注 小事大事 最近看到人家穿针织衫都很好看,想买一件,于是和老公说。我:“老公,我想买一件针织衫。”老公:“想买就买,这么小的事情不用和我商量,我是管大事的。”转头老公想拿个桃吃,发现桃不见了。老公:“那个桃呢?”我:“我吃了。”老公:“这么大的事你都不和我商量一下,我没同意你怎么能吃呢。” 0 0 0 何大宝 2019-02-19 + 关注 小狗：我冤枉啊 &nbsp; &nbsp; &nbsp;邻居很不孝顺，这天他妈来要生活费，又被他骂哭了，大家虽然看不惯，但也没法吭声。刚好我牵着我家的两条狗在邻居门口遛，我家的两条狗是一大一小，母子关系。看着邻居骂骂咧咧，死活不给生活费，突然我狠狠踹了我家的小狗一脚，骂道:“你这个狗东西，自己吃得饱饱的，还抢你妈嘴里的，你让你妈饿着你舒坦是吧？你这个不肖的东西，不办人事，就算老天给你一张人皮你也是畜生。”四周的邻居都来围观我骂狗，都说骂得好，狠狠地骂，我也越骂越顺口，后来我老婆来了，我以为她来劝我回家少惹事，谁知道她默默地递给我一瓶矿泉水 …&nbsp; zylrocket 你就是这样被你邻居暴打一顿的！ 1 1 7 何大宝 2019-02-19 + 关注 很久没洗澡了 丈夫当了个小官，自然少不了应酬。但是妻子不放心，怕在外面乱搞。一日，丈夫回来很晚，原准备了很多解释的词，回来一见妻子已睡，喜出望外，心随之放松了。睡下，妻子挨上来亲热，首先用脚搓了搓丈夫的脚背，问：“怎么脚这么滑呀？”丈夫回答说是今天按摩院洗足浴，没办法，要应酬。一阵子后，妻子抚着丈夫的胸再问：“身上怎么这么滑呀！”丈夫说：“今天 ……”说到半句，突然感觉不对，赶紧改口，“这不关小姐的事，是我很久没洗澡了。 0 0 0 粉色娘子军 2019-02-01 + 关注 孝顺挺好，可泡脚需谨慎呀","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201903\/22032014_70951.jpg","addtime":"2019-03-22 03:20:14","url":"http:\/\/m.kaixinhui.com\/detail-128080.html"},{"content":"摩托车开好了回家吃饭， 开不好全村人去你家吃饭！ 0 0 2 采妮 2019-01-23 + 关注 一起来嗨！摇起来","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201903\/19032123_92643.jpg","addtime":"2019-03-19 03:21:23","url":"http:\/\/m.kaixinhui.com\/detail-127761.html"},{"content":"小狗：我冤枉啊 &nbsp; &nbsp; &nbsp;邻居很不孝顺，这天他妈来要生活费，又被他骂哭了，大家虽然看不惯，但也没法吭声。刚好我牵着我家的两条狗在邻居门口遛，我家的两条狗是一大一小，母子关系。看着邻居骂骂咧咧，死活不给生活费，突然我狠狠踹了我家的小狗一脚，骂道:“你这个狗东西，自己吃得饱饱的，还抢你妈嘴里的，你让你妈饿着你舒坦是吧？你这个不肖的东西，不办人事，就算老天给你一张人皮你也是畜生。”四周的邻居都来围观我骂狗，都说骂得好，狠狠地骂，我也越骂越顺口，后来我老婆来了，我以为她来劝我回家少惹事，谁知道她默默地递给我一瓶矿泉水 …&nbsp; zylrocket 你就是这样被你邻居暴打一顿的！ 1 1 7 何大宝 2019-02-19 + 关注 很久没洗澡了 丈夫当了个小官，自然少不了应酬。但是妻子不放心，怕在外面乱搞。一日，丈夫回来很晚，原准备了很多解释的词，回来一见妻子已睡，喜出望外，心随之放松了。睡下，妻子挨上来亲热，首先用脚搓了搓丈夫的脚背，问：“怎么脚这么滑呀？”丈夫回答说是今天按摩院洗足浴，没办法，要应酬。一阵子后，妻子抚着丈夫的胸再问：“身上怎么这么滑呀！”丈夫说：“今天 ……”说到半句，突然感觉不对，赶紧改口，“这不关小姐的事，是我很久没洗澡了。 0 1 0 粉色娘子军 2019-02-01 + 关注 孝顺挺好，可泡脚需谨慎呀","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201903\/19032051_57376.jpg","addtime":"2019-03-19 03:20:51","url":"http:\/\/m.kaixinhui.com\/detail-128069.html"},{"content":"就怕小偷有文化。","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032115_28518.jpg","addtime":"2019-02-02 03:21:15","url":"http:\/\/m.kaixinhui.com\/detail-127948.html"},{"content":"誓死捍卫自己的零食","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032115_11955.jpg","addtime":"2019-02-02 03:21:15","url":"http:\/\/m.kaixinhui.com\/detail-127947.html"},{"content":"凶器随身带，没想到你是这样的戚哥","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032115_81876.jpg","addtime":"2019-02-02 03:21:15","url":"http:\/\/m.kaixinhui.com\/detail-127946.html"},{"content":"坑老鼠","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032114_25155.jpg","addtime":"2019-02-02 03:21:14","url":"http:\/\/m.kaixinhui.com\/detail-127945.html"},{"content":"好凉好凉","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032114_57179.jpg","addtime":"2019-02-02 03:21:14","url":"http:\/\/m.kaixinhui.com\/detail-127944.html"},{"content":"这样的女朋友还能不能要","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032114_60454.jpg","addtime":"2019-02-02 03:21:14","url":"http:\/\/m.kaixinhui.com\/detail-127943.html"},{"content":"年轻时做下的莽撞事","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032114_93232.jpg","addtime":"2019-02-02 03:21:14","url":"http:\/\/m.kaixinhui.com\/detail-127942.html"},{"content":"这~真黑！","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032114_63192.jpg","addtime":"2019-02-02 03:21:14","url":"http:\/\/m.kaixinhui.com\/detail-127941.html"},{"content":"几百年后肯定值钱！","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032113_62904.jpg","addtime":"2019-02-02 03:21:13","url":"http:\/\/m.kaixinhui.com\/detail-127940.html"},{"content":"寒风大雪也抵挡不住我玩游戏的热情","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032113_78961.jpg","addtime":"2019-02-02 03:21:13","url":"http:\/\/m.kaixinhui.com\/detail-127939.html"},{"content":"哥们，味道够不","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032112_48394.jpg","addtime":"2019-02-02 03:21:12","url":"http:\/\/m.kaixinhui.com\/detail-127938.html"},{"content":"八戒，师傅何曾饿过你，竟这般没出息","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032111_21461.jpg","addtime":"2019-02-02 03:21:11","url":"http:\/\/m.kaixinhui.com\/detail-127937.html"},{"content":"还没开始就已经结束了！","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032111_94138.jpg","addtime":"2019-02-02 03:21:11","url":"http:\/\/m.kaixinhui.com\/detail-127936.html"},{"content":"吃得啥？不像是黄瓜啊","pic":"http:\/\/api.jisuapi.com\/xiaohua\/upload\/201902\/02032111_34673.jpg","addtime":"2019-02-02 03:21:11","url":"http:\/\/m.kaixinhui.com\/detail-127935.html"}]}}

         */
    }
}