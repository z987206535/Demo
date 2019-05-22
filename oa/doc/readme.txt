
1:新建项目
2:修改项目编码 utf-8
3:分析功能创建表 db/oa.sql
4:导入表
  set names utf8;
  source d:/oa.sql
  
机构管理
  1:分页显示机构管理列表
  2:删除
  3:添加机构
  4:返回  
  
5:DAO
    实例类          *Org
  DAO接口        *IOrgDAO    *DAOFactory
   实现接口类    *OrgDAOMySQLImpl
   业务类接口    OrgService
   实现类          OrgServierImpl
   工具类          DBUtils/ConfigUtils
 包结构
   cn.tedu.oa.domain    实体类
   cn.tedu.oa.dao       DAO接口
   cn.tedu.oa.service   业务接口与实现类
   cn.tedu.oa.utils     工具类
    ApplicationException
    DBUtils
    ConfigUtils
    config.properties
   cn.tedu.oa.web       控制器
   cn.tedu.oa.test      测试类
   
    