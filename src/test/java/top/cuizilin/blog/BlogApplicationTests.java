package top.cuizilin.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.cuizilin.blog.dao.BlogMapper;
import top.cuizilin.blog.dao.CommentMapper;
import top.cuizilin.blog.dao.TypeMapper;
import top.cuizilin.blog.pojo.Blog;
import top.cuizilin.blog.pojo.Comment;
import top.cuizilin.blog.service.CommentService;
import top.cuizilin.blog.utils.MarkDownUtils;

import java.util.Date;
import java.util.List;

@SpringBootTest
@MapperScan("top.cuizilin.blog.dao")
class BlogApplicationTests {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @Test
    public void test(){
        System.out.println(MarkDownUtils.markdownToHtmlExtensions("# \t\t\t\t\tjQuery学习笔记01(选择器)\n" +
                "\n" +
                "## 1、jQuery对象转化为DOM对象\n" +
                "\n" +
                "```javascript\n" +
                "var $obj = $(\"#Obj\") //$obj是jQuery对象\n" +
                "var obj = $obj[0] //方法1，通过下标获取\n" +
                "\n" +
                "var obj = $obj.get(0) //方法2，通过get加下标获取\n" +
                "```\n" +
                "\n" +
                "## 2、dom对象转化为jQuery对象\n" +
                "\n" +
                "```javascript\n" +
                "var obj = document.getElementById(\"obj\"); //obj为DOM对象\n" +
                "var $obj = $(obj) //直接加$符就可以\n" +
                "```\n" +
                "\n" +
                "## 3、常见css选择器\n" +
                "\n" +
                "```css\n" +
                "#obj{} /*id选择器*/\n" +
                ".obj{} /*类选择器*/\n" +
                "body{} /*标签选择器*/\n" +
                "E1,E2,E3{} /*群组选择器*/\n" +
                "E F{} /*后代选择器*/\n" +
                "*{} /*通配符选择器*/\n" +
                "```\n" +
                "\n" +
                "## 4、 jQuery判断一个元素是否存在\n" +
                "\n" +
                "```html\n" +
                "<input type=\"text\" id=\"ipt\">\n" +
                "<button type=\"button\" class=\"mybtn\">点击</button>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(\".mybtn\").click(function (){\n" +
                "        alert($(\"#ipt\").length) // 1   //jQuery判断元素是否存在$().length,存在为1，不存在为0\n" +
                "    })\n" +
                "</script>\n" +
                "```\n" +
                "\n" +
                "## 5、jQuery层次选择器\n" +
                "\n" +
                "#### \t1、$(\"父元素 子元素\")（中间有空格）\n" +
                "\n" +
                "```html\n" +
                "<div class=\"mydiv\">\n" +
                "    <span class=\"myspan\">1</span>\n" +
                "    <span>1</span>\n" +
                "</div>\n" +
                "\n" +
                "<div>\n" +
                "    <span>3</span>\n" +
                "    <span>3</span>\n" +
                "</div>\n" +
                "\n" +
                "<button type=\"button\" class=\"mybtn\">使得class为mydiv里的span的值发生变化</button>\n" +
                "<button type=\"button\" class=\"mybtn2\">所有div里span的值发生变化</button>\n" +
                "<button type=\"button\" class=\"mybtn3\">使得class为mydiv里的class为myspan的span的值发生变化</button>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(\".mybtn\").click(function (){\n" +
                "        $(\".mydiv span\").html(2)\n" +
                "    })\n" +
                "    $(\".mybtn2\").click(function (){\n" +
                "        $(\"div span\").html(2)\n" +
                "    })\n" +
                "    \n" +
                "    $(\".mybtn3\").click(function (){\n" +
                "        $(\".mydiv .myspan\").html(2) //最常用\n" +
                "    })\n" +
                "</script>\n" +
                "```\n" +
                "\n" +
                "#### \t2、$(\"父元素 > 子元素\")（中间用大于号连接, 和第一个的唯一区别是 只会选择最外层子元素）\n" +
                "\n" +
                "```html\n" +
                "<div class=\"ui segment\">\n" +
                "    <div class=\"ui segment\"></div>\n" +
                "    <div class=\"ui segment\"></div>\n" +
                "    <div class=\"ui segment\"></div>\n" +
                "    <div class=\"ui segment\"></div>\n" +
                "</div>\n" +
                "\n" +
                "<button type=\"button\" id=\"btn1\">点击使body中所有div变色</button>\n" +
                "<button type=\"button\" id=\"btn2\">点击使body中最外层div变色</button>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(\"#btn1\").click(function (){\n" +
                "        $(\"body div\").css(\"background\", \"red\");\n" +
                "    })\n" +
                "\n" +
                "    $(\"#btn2\").click(function (){\n" +
                "        $(\"body > div\").css(\"background\", \"red\");\n" +
                "    })\n" +
                "</script>\n" +
                "```\n" +
                "\n" +
                "#### \t3、$(\"prev + next\")（选取prev元素后第一个同辈next元素，等价于$(\"prev\").next(\"next\")   ）\n" +
                "\n" +
                "#### \t4、$(\"prev ~ siblings\")（选取prev元素所有的同辈siblings元素, 等价于$(\"prev\").nextAll(\"siblings\")  ）\n" +
                "\n" +
                "## 6、表单选择器\n" +
                "\n" +
                "| 选择器    | 描述                                        | 实例 |\n" +
                "| --------- | ------------------------------------------- | ---- |\n" +
                "| :input    | 选取所有的<input><button><select><textarea> |      |\n" +
                "| :text     | 选择所有的单行文本框                        |      |\n" +
                "| :password | 选取所有密码框                              |      |\n" +
                "| :radio    | 选取所有单选框                              |      |\n" +
                "| :checkbox | 选取所有复选框                              |      |\n" +
                "| :submit   | 选取所有提交按钮                            |      |\n" +
                "| :image    | 选取所有图片                                |      |\n" +
                "| :reset    | 选取所有重置按钮                            |      |\n" +
                "| :button   | 选取所有按钮                                |      |\n" +
                "| :file     | 选取所有上传域                              |      |\n" +
                "| :hidden   | 选择所有隐藏域                              |      |\n" +
                "\n" +
                "\n" +
                "\n" +
                "## 7、jQuery过滤选择器（太简单，只介绍）\n" +
                "\n" +
                "#### \t1)基本过滤选择器\n" +
                "\n" +
                "| 选择器         | 描述                   | 返回 |\n" +
                "| -------------- | ---------------------- | ---- |\n" +
                "| :fisrt         | 选取第一个元素         | 单个 |\n" +
                "| :last          | 选取最后一个元素       | 单个 |\n" +
                "| :not(selector) | 选取不匹配的元素       | 集合 |\n" +
                "| :even          | 选取索引是偶数         | 集合 |\n" +
                "| :odd           | 索引为奇数             | 集合 |\n" +
                "| :eq(index)     | 根据索引选择           | 单个 |\n" +
                "| :gt(index)     | 大于索引的元素         | 集合 |\n" +
                "| :lt(index)     | 小于索引的元素         | 集合 |\n" +
                "| :header        | 选取所有标题           | 集合 |\n" +
                "| :focus         | 选取所有获取焦点的元素 | 集合 |\n" +
                "\n" +
                "#### \t2)内容过滤选择器\n" +
                "\n" +
                "| 选择器         | 描述                         | 实例                                              |\n" +
                "| -------------- | ---------------------------- | ------------------------------------------------- |\n" +
                "| :contains      | 选取含有文本为text的元素     | $(\"div:contains('我')\"),选择所有含有'我'的div元素 |\n" +
                "| :empty         | 选取不包含子元素或文本的元素 | $(\"div:empty\")选取所有不含子元素（包括文本）的div |\n" +
                "| :has(selector) | 选择含有选择器匹配的元素     | $(\"div:has(p)\")选取含有标签p的div元素             |\n" +
                "| :parent        | 与empty相反                  | $(\"div:parent\")选取含有子元素或文本的div          |\n" +
                "\n" +
                "#### \t3）可见性过滤选择器\n" +
                "\n" +
                "| 选择器   | 描述               | 实例                                                         |\n" +
                "| -------- | ------------------ | ------------------------------------------------------------ |\n" +
                "| :hidden  | 选取所有不可见元素 | $(\":hidden\")选取包括<input type=\"hidden\">和<div style=\"visibility:none\">和<div style=\"display:none\">之类的元素 $(\"input:hidden\")选取所有隐藏域 |\n" +
                "| :visible | 与hidden相反       |                                                              |\n" +
                "\n" +
                "#### \t4）属性过滤选择器\n" +
                "\n" +
                "| 选择器                   | 描述                    | 实例                                           |\n" +
                "| ------------------------ | ----------------------- | ---------------------------------------------- |\n" +
                "| [attribute]              | 选择拥有此属性的元素    | $(\"div[id]\")选取拥有id属性的div                |\n" +
                "| [attribute=value]        | 选择属性值为value的元素 | $(\"div[title=test]\")选取title为\"test\"的div元素 |\n" +
                "| [attribute!=value]       | 与上一个相反            |                                                |\n" +
                "| [attribute^=value]       | 属性值以value开始       |                                                |\n" +
                "| [attribute$=value]       | 属性值以value结束       |                                                |\n" +
                "| [attribute1][attribute2] | 复合选择器              |                                                |\n" +
                "\n" +
                "#### \t5)表单属性过滤器*****\n" +
                "\n" +
                "| 选择器    | 描述                                 | 实例                                                  |\n" +
                "| --------- | ------------------------------------ | ----------------------------------------------------- |\n" +
                "| :enabled  | 选取可用元素                         | $(\"#form1 :enabled\")选取id为form1的表单内所有可用元素 |\n" +
                "| :disabled | 与上一个相反                         |                                                       |\n" +
                "| :checked  | 获取所有被选中的元素(单选框，复选框) | $(\"input:checked\")选取所有被选中的input               |\n" +
                "| :selected | 选取所有被选中的下拉选项             | $(\"select option:selected\")选取所有被选中的下拉选项   |\n" +
                "\n" +
                "#### \t简单实例\n" +
                "\n" +
                "```html\n" +
                "<form action=\"\" id=\"form1\">\n" +
                "    <input type=\"text\" value=\"可用文本框\">\n" +
                "    <input type=\"text\" disabled=\"disabled\" value=\"不可用文本框\">\n" +
                "\n" +
                "    <input type=\"checkbox\" checked=\"checked\" value=\"选项1\">\n" +
                "    <input type=\"checkbox\"  value=\"选项2\">\n" +
                "    <input type=\"checkbox\"  value=\"选项3\">\n" +
                "    <input type=\"checkbox\" checked=\"checked\" value=\"选项4\">\n" +
                "\n" +
                "    <select multiple=\"multiple\">\n" +
                "        <option selected=\"selected\">1</option>\n" +
                "        <option>2</option>\n" +
                "        <option selected=\"selected\">3</option>\n" +
                "        <option>4</option>\n" +
                "        <option selected=\"selected\">5</option>\n" +
                "    </select>\n" +
                "\n" +
                "</form>\n" +
                "\n" +
                "\n" +
                "<button type=\"button\" id=\"btn1\">点击弹出value为可用文本框的value值</button>\n" +
                "<button type=\"button\" id=\"btn2\">点击弹出所有被选中的checkbox的个数</button>\n" +
                "<button type=\"button\" id=\"btn3\">点击弹出下拉列表的值</button>\n" +
                "\n" +
                "<script type=\"text/javascript\">\n" +
                "    $(\"#btn1\").click(function (){\n" +
                "       alert($(\"#form1 :enabled\").val());\n" +
                "    })\n" +
                "\n" +
                "    $(\"#btn2\").click(function (){\n" +
                "        alert($(\"#form1 input:checked\").length)\n" +
                "    })\n" +
                "\n" +
                "    $(\"#btn3\").click(function (){\n" +
                "        alert($(\"#form1 select option:selected\").text())\n" +
                "    })\n" +
                "\n" +
                "</script>\n" +
                "```\n" +
                "\n"));
    }


}
