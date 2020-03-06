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
 * @Date: 2020/3/4 15:27
 * @Description:
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {AlidemoApplication.class})
public  class SelfLifeServiceTest {
	@Autowired
	private SelfLifeService selfLifeService;

	@Test
	public void testSlefLife(){
		HashMap<String, String> reqMap = new HashMap<>(16);
		reqMap.put("host","http://fzlq.market.alicloudapi.com");
		reqMap.put("path","/ai_metaphysics/fo_zu_lin_qian/elite");
		reqMap.put("BIRTH","19960316000000");
		reqMap.put("FIRST_NAME","张");
		reqMap.put("GENDER","男");
		reqMap.put("LAST_NAME","无忌");
		/*
		ret:
		{"FIRT_NAME": "张", "LAST_NAME": "无忌", "BIRTH": "20180808", "YEAR": "戊戌", "MONTH": "六月", "DAY": "廿七", "HOUR": "辰时", "ANIMAL": "狗", "GENDER": "男", "SIGN_NAME": "佛祖灵签", "SIGN_ID": "47", "SIGN_TYPE": "中平签", "SIGN_TITLE": "许宁祖平番", "SIGN_POEM": "月边添六九，牛尾两旁开；且待浮云散，光明依旧同", "SIGN_INTRO": "密云不雨，雨湿桃腮之兆", "SIGN_ENTITY": {"SIGN_CAREER": "得陇望蜀，因地制宜，出生入死，万箭穿心", "SIGN_FAMILY": "暗度陈仓，金枝玉叶，落花流水，昙花一现", "SIGN_EMOTION": "越俎代庖，难得糊涂，因果报应，差强人意", "SIGN_ACADEMIC": "黔驴技穷，完璧归赵，大浪淘沙，天方夜谭", "SIGN_INVEST": "酸甜苦辣，沧海桑田，患得患失，出生入死", "SIGN_HEALTH": "蝇营狗苟，不耻下问，退避三舍，矫枉过正", "SIGN_SWITCH": "鸠占鹊巢，明日黄花，患得患失，蝇营狗苟", "SIGN_LAWSUIT": "南柯一梦，运筹帷幄，画地为牢，坐井观天", "SIGN_LOST": "矫枉过正，十字路口，天方夜谭，龙潭虎穴", "SIGN_TRAVEL": "姗姗来迟，不一而足，塞翁失马，高山流水", "SIGN_CHILD": "望梅止渴，愚公移山，空穴来风，昙花一现"}}
		 */
		this.selfLifeService.getSlefLife(reqMap);
		/*
		{
    "FIRT_NAME":"张",                                                                #姓氏
    "LAST_NAME":"无忌",                                                              #名称
    "BIRTH":"20180808",                                                              #生日
    "YEAR":"戊戌",                                                                   #农历年份
    "MONTH":"六月",                                                                  #农历月份
    "DAY":"廿七",                                                                    #农历日期
    "HOUR":"辰时",                                                                   #农历时辰
    "ANIMAL":"狗",                                                                   #属相
    "GENDER":"男",                                                                   #性别
    "SIGN_NAME":"观音灵签",                                                          #签名称
    "SIGN_ID":"49",                                                                  #签号
    "SIGN_TYPE":"中平签",                                                            #签类型
    "SIGN_TITLE":"王祥求鲤",                                                         #签标题
    "SIGN_POEM":"天寒地冻水成冰，何须贪吝取功名；只好守己静处坐，待时兴变自然明",    #签诗
    "SIGN_INTRO":"此卦水结成冰之象，凡事不用枉求也",                                 #签诗注解
    "SIGN_ENTITY":{                                                                  #签属性实体信息
        "SIGN_CAREER":"叶公好龙，劫后余生，逐鹿中原，十万火急",                      #签实体_事业注解
        "SIGN_FAMILY":"难兄难弟，时光荏苒，川流不息，浪子回头金不换",                #签实体_家庭注解
        "SIGN_EMOTION":"差强人意，亡羊补牢，白云苍狗，心猿意马",                     #签实体_情感注解
        "SIGN_ACADEMIC":"迫在眉睫，退避三舍，白驹过隙，奇货可居",                    #签实体_学业注解
        "SIGN_INVEST":"兵临城下，精卫填海，海枯石烂，全力以赴",                      #签实体_投资注解
        "SIGN_HEALTH":"饮鸩止渴，墨守成规，勿谓言之不预也，心有灵犀一点通",          #签实体_健康注解
        "SIGN_SWITCH":"天方夜谭，竭泽而渔，星火燎原，九九归一",                      #签实体_转换注解
        "SIGN_LAWSUIT":"釜底抽薪，井底之蛙，殚精竭虑，乘风破浪",                     #签实体_官司注解
        "SIGN_LOST":"南柯一梦，望洋兴叹，人心不足蛇吞象，单刀直入",                  #签实体_寻物注解
        "SIGN_TRAVEL":"酸甜苦辣，杯水车薪，全力以赴，秦晋之好",                      #签实体_出行注解
        "SIGN_CHILD":"吹毛求疵，患得患失，走马观花，星火燎原"                        #签实体_求子注解
    }
}
		 */
	}
}