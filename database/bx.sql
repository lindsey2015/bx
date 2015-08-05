/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.21-log : Database - bx
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bx` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bx`;

/*Table structure for table `tb_age_group` */

DROP TABLE IF EXISTS `tb_age_group`;

CREATE TABLE `tb_age_group` (
  `id` varchar(255) NOT NULL,
  `age_group` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_age_group` */

insert  into `tb_age_group`(`id`,`age_group`,`product_id`) values 
  ('f89957fc4d66e1d1014d6b3f2be40003','1-80','f89957fc4d66e1d1014d6b3f138a0002'),
  ('f89957fc4d66e1d1014d98d33763000d','1-17','f89957fc4d66e1d1014d98d30835000c'),
  ('f89957fc4d66e1d1014d98d355f7000e','18-80','f89957fc4d66e1d1014d98d30835000c');

/*Table structure for table `tb_bd_info` */

DROP TABLE IF EXISTS `tb_bd_info`;

CREATE TABLE `tb_bd_info` (
  `id` varchar(255) NOT NULL,
  `age_group` varchar(255) DEFAULT NULL,
  `bd_no` varchar(255) DEFAULT NULL,
  `create_time` varchar(255) DEFAULT NULL,
  `days` varchar(255) DEFAULT NULL,
  `excel_addr` varchar(255) DEFAULT NULL,
  `no` int(11) NOT NULL,
  `nums` varchar(255) DEFAULT NULL,
  `pdf_addr` varchar(255) DEFAULT NULL,
  `start_day` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `tb_no` varchar(255) DEFAULT NULL,
  `total` double NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_bd_info` */

/*Table structure for table `tb_bz_date` */

DROP TABLE IF EXISTS `tb_bz_date`;

CREATE TABLE `tb_bz_date` (
  `id` varchar(255) NOT NULL,
  `age_group_id` varchar(255) DEFAULT NULL,
  `max_day` int(11) NOT NULL,
  `min_day` int(11) NOT NULL,
  `value` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_bz_date` */

insert  into `tb_bz_date`(`id`,`age_group_id`,`max_day`,`min_day`,`value`) values 
  ('f89957fc4d66e1d1014d6b3f46a00004','f89957fc4d66e1d1014d6b3f2be40003',1,1,3),
  ('f89957fc4d66e1d1014d98cbf3530006','f89957fc4d66e1d1014d6b3f2be40003',2,2,4),
  ('f89957fc4d66e1d1014d98cc14670007','f89957fc4d66e1d1014d6b3f2be40003',3,3,7),
  ('f89957fc4d66e1d1014d98cc46e10008','f89957fc4d66e1d1014d6b3f2be40003',5,4,8),
  ('f89957fc4d66e1d1014d98cc75060009','f89957fc4d66e1d1014d6b3f2be40003',9,6,15),
  ('f89957fc4d66e1d1014d98ccbe92000a','f89957fc4d66e1d1014d6b3f2be40003',15,10,25),
  ('f89957fc4d66e1d1014d98d39af0000f','f89957fc4d66e1d1014d98d33763000d',1,1,8),
  ('f89957fc4d66e1d1014d98d3c6650010','f89957fc4d66e1d1014d98d33763000d',2,2,12),
  ('f89957fc4d66e1d1014d98d3ed940011','f89957fc4d66e1d1014d98d33763000d',3,3,16),
  ('f89957fc4d66e1d1014d98d4139b0012','f89957fc4d66e1d1014d98d33763000d',4,4,24),
  ('f89957fc4d66e1d1014d98d440970013','f89957fc4d66e1d1014d98d33763000d',5,5,25),
  ('f89957fc4d66e1d1014d98d47d610014','f89957fc4d66e1d1014d98d33763000d',7,6,28),
  ('f89957fc4d66e1d1014d98d4b8a50015','f89957fc4d66e1d1014d98d33763000d',10,8,35),
  ('f89957fc4d66e1d1014d98d4eef80016','f89957fc4d66e1d1014d98d33763000d',13,11,45),
  ('f89957fc4d66e1d1014d98d51de80017','f89957fc4d66e1d1014d98d33763000d',16,14,50),
  ('f89957fc4d66e1d1014d98d55fe20018','f89957fc4d66e1d1014d98d33763000d',19,17,55),
  ('f89957fc4d66e1d1014d98d5e86c0019','f89957fc4d66e1d1014d98d33763000d',26,20,80),
  ('f89957fc4d66e1d1014d98d62566001a','f89957fc4d66e1d1014d98d33763000d',33,27,90),
  ('f89957fc4d66e1d1014d98d657b1001b','f89957fc4d66e1d1014d98d33763000d',40,34,100),
  ('f89957fc4d66e1d1014d98d69f2a001c','f89957fc4d66e1d1014d98d355f7000e',1,1,13),
  ('f89957fc4d66e1d1014d98d6c36b001d','f89957fc4d66e1d1014d98d355f7000e',2,2,17),
  ('f89957fc4d66e1d1014d98d6e182001e','f89957fc4d66e1d1014d98d355f7000e',3,3,22),
  ('f89957fc4d66e1d1014d98d70612001f','f89957fc4d66e1d1014d98d355f7000e',4,4,25),
  ('f89957fc4d66e1d1014d98d76aa80020','f89957fc4d66e1d1014d98d355f7000e',5,5,30),
  ('f89957fc4d66e1d1014d98d792930021','f89957fc4d66e1d1014d98d355f7000e',6,6,40),
  ('f89957fc4d66e1d1014d98d7b8a90022','f89957fc4d66e1d1014d98d355f7000e',7,7,43),
  ('f89957fc4d66e1d1014d98d7e0270023','f89957fc4d66e1d1014d98d355f7000e',10,8,45),
  ('f89957fc4d66e1d1014d98d812ef0024','f89957fc4d66e1d1014d98d355f7000e',13,11,47),
  ('f89957fc4d66e1d1014d98d850f20025','f89957fc4d66e1d1014d98d355f7000e',16,14,52),
  ('f89957fc4d66e1d1014d98d887f00026','f89957fc4d66e1d1014d98d355f7000e',19,17,60),
  ('f89957fc4d66e1d1014d98d8c5c50027','f89957fc4d66e1d1014d98d355f7000e',26,20,95),
  ('f89957fc4d66e1d1014d98d9058d0028','f89957fc4d66e1d1014d98d355f7000e',33,27,130),
  ('f89957fc4d66e1d1014d98d977620029','f89957fc4d66e1d1014d98d355f7000e',40,34,155);

/*Table structure for table `tb_catagory` */

DROP TABLE IF EXISTS `tb_catagory`;

CREATE TABLE `tb_catagory` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `namee` varchar(255) DEFAULT NULL,
  `useful` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_catagory` */

insert  into `tb_catagory`(`id`,`name`,`namee`,`useful`) values 
  ('f89957fc4d66e1d1014d6b3eb2fb0001','太平洋户外之旅','HW',1),
  ('f89957fc4d66e1d1014d98d16ac0000b','太平洋国内紧急救援','ZJ',1);

/*Table structure for table `tb_product` */

DROP TABLE IF EXISTS `tb_product`;

CREATE TABLE `tb_product` (
  `id` varchar(255) NOT NULL,
  `catagory_id` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `useful` tinyint(1) NOT NULL,
  `tb_area` varchar(10000) DEFAULT NULL,
  `tk_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_product` */

insert  into `tb_product`(`id`,`catagory_id`,`name`,`useful`,`tb_area`,`tk_addr`) values
 ('f89957fc4d66e1d1014d6b3f138a0002','f89957fc4d66e1d1014d6b3eb2fb0001','太平洋户外之旅-图途专享',1,'<div align=\"center\">\n	<table class=\"MsoNormalTable\" border=\"1\" cellpadding=\"0\" width=\"567\" style=\"width:425.4pt;border:solid #D8D8D8 1.0pt;\">\n		<tbody>\n			<tr>\n				<td width=\"84\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<a name=\"OLE_LINK63\"></a><a name=\"OLE_LINK62\"></a><a name=\"OLE_LINK61\"></a><b><span style=\"font-size:9.0pt;font-family:宋体;\">保险责任</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b> \n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<b><span style=\"font-size:9.0pt;font-family:宋体;\">保险金额</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b> \n					</p>\n				</td>\n				<td width=\"402\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<b><span style=\"font-size:9.0pt;font-family:宋体;\">保障说明</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b> \n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"84\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">意外身故、伤残<span></span></span> \n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">10</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元<span></span></span> \n					</p>\n				</td>\n				<td width=\"402\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">在旅行期间遭遇意外事故，保险公司按保险单上所载被保险人相应的保险金额给付身故保险金、伤残保险金。（不包含高风险活动）<span></span></span> \n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"84\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">高风险运动意外身故、伤残<span></span></span> \n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">10</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元<span></span></span> \n					</p>\n				</td>\n				<td width=\"402\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">在保险期间内，保险公司扩展承保被保险人在进行跳伞、潜水、攀岩、探险活动等休闲娱乐性高风险运动的过程中遭受的意外伤害事故，并根据主保险合同约定的赔偿项目承担给付保险金的责任。<span></span></span> \n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"84\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">意外伤害医疗<span></span></span> \n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">1.5</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元<span></span></span> \n					</p>\n				</td>\n				<td width=\"402\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">保险期间内，被保险人在旅行期间遭受意外伤害事故，并在符合本条款释义的医院进行治疗，保险公司就被保险人自事故发生之日起<span>180</span>日内实际支出的按照当地社会医疗保险主管部门规定可报销的、必要的、合理的医疗费用超过人民币<span>100</span>元的部分，给付医疗保险金。 （包含高风险运动意外医疗，免赔<span>100</span>元，<span>100%</span>赔付）<span></span></span> \n					</p>\n				</td>\n			</tr>\n		</tbody>\n	</table>\n</div>\n<br />',''),
 ('f89957fc4d66e1d1014d98d30835000c','f89957fc4d66e1d1014d98d16ac0000b','太平洋国内旅行紧急救援计划-图途专享',1,'<div align=\"center\">\n	<table class=\"MsoNormalTable\" border=\"1\" cellpadding=\"0\" width=\"579\" style=\"width:434.25pt;border:solid #D8D8D8 1.0pt;\">\n		<tbody>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<a name=\"OLE_LINK7\"></a><a name=\"OLE_LINK6\"></a><a name=\"OLE_LINK5\"></a><b><span style=\"font-size:9.0pt;font-family:宋体;\">保险责任</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<b><span style=\"font-size:9.0pt;font-family:宋体;\">保险金额</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<b><span style=\"font-size:9.0pt;font-family:宋体;\">保障说明</span></b><b><span style=\"font-size:9.0pt;font-family:&quot;\"></span></b>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">意外身故、伤残保障</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">20</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">未满</span><span style=\"font-size:9.0pt;font-family:&quot;\">18</span><span style=\"font-size:9.0pt;font-family:宋体;\">周岁的未成年人的</span><span style=\"font-size:9.0pt;font-family:&quot;\">\"</span><span style=\"font-size:9.0pt;font-family:宋体;\">意外身故、伤残保障</span><span style=\"font-size:9.0pt;font-family:&quot;\">\"</span><span style=\"font-size:9.0pt;font-family:宋体;\">的保险金为</span><span style=\"font-size:9.0pt;font-family:&quot;\">10</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">医药补偿</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">3</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">境内旅行意外或突发疾病医药补偿</span><span style=\"font-size:9.0pt;font-family:&quot;\">&nbsp; </span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">每日住院津贴</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">50</span><span style=\"font-size:9.0pt;font-family:宋体;\">元</span><span style=\"font-size:9.0pt;font-family:&quot;\">/</span><span style=\"font-size:9.0pt;font-family:宋体;\">天</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">总赔偿日数以</span><span style=\"font-size:9.0pt;font-family:&quot;\">90</span><span style=\"font-size:9.0pt;font-family:宋体;\">天为限。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">慰问探访费用补偿</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">8000</span><span style=\"font-size:9.0pt;font-family:宋体;\">元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">遭受主险合同责任范围内的意外伤害，<a name=\"OLE_LINK14\"></a><a name=\"OLE_LINK13\"></a><a name=\"OLE_LINK12\"></a><a name=\"OLE_LINK11\"></a><a name=\"OLE_LINK10\"></a><a name=\"OLE_LINK9\"></a>在中华人民共和国境内二级以上（含二级）或保险人认可的医疗机构住院治疗且连续超过三天或保单约定的天数，被保险人亲属前往探望的，保险人按照条款标准的金额赔偿探望实际发生的酒店住宿费、餐饮费和交通费。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">医疗运送和送返</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">10</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">若被保险人罹患突发疾病或遭受意外事故</span><span style=\"font-size:9.0pt;font-family:&quot;\">,</span><span style=\"font-size:9.0pt;font-family:宋体;\">保险公司指定的服务机构安排及提供医疗运送和送返的服务。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">身故遗体送返</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">2</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">旅行延误</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">600</span><span style=\"font-size:9.0pt;font-family:宋体;\">元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">5</span><span style=\"font-size:9.0pt;font-family:宋体;\">小时延误起，每次延误赔偿</span><span style=\"font-size:9.0pt;font-family:&quot;\">300</span><span style=\"font-size:9.0pt;font-family:宋体;\">元；最高为</span><span style=\"font-size:9.0pt;font-family:&quot;\">600</span><span style=\"font-size:9.0pt;font-family:宋体;\">元。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">未成年人旅行送返费用补偿</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">2000</span><span style=\"font-size:9.0pt;font-family:宋体;\">元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">不适用于未成年人。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n			<tr>\n				<td width=\"94\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">旅行绑架及非法拘禁</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"74\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"center\" style=\"text-align:center;\">\n						<span style=\"font-size:9.0pt;font-family:&quot;\">1.2</span><span style=\"font-size:9.0pt;font-family:宋体;\">万元</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n				<td width=\"403\" style=\"border:solid #D8D8D8 1.0pt;\">\n					<p class=\"MsoNormal\" align=\"left\">\n						<span style=\"font-size:9.0pt;font-family:宋体;\">每</span><span style=\"font-size:9.0pt;font-family:&quot;\">24</span><span style=\"font-size:9.0pt;font-family:宋体;\">小时赔偿额为</span><span style=\"font-size:9.0pt;font-family:&quot;\">3,000</span><span style=\"font-size:9.0pt;font-family:宋体;\">元。</span><span style=\"font-size:9.0pt;font-family:&quot;\"></span>\n					</p>\n				</td>\n			</tr>\n		</tbody>\n	</table>\n</div>','/upload/docs/20150528124113866439.doc');

/*Table structure for table `tb_user` */

DROP TABLE IF EXISTS `tb_user`;

CREATE TABLE `tb_user` (
  `id` varchar(255) NOT NULL,
  `password` varchar(25) NOT NULL,
  `type` int(11) NOT NULL,
  `useful` tinyint(1) NOT NULL,
  `username` varchar(25) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user` */

insert  into `tb_user`(`id`,`password`,`type`,`useful`,`username`) values 
  ('34535436224245','12345',1,1,'superadmin'),
  ('5746353','12345',2,1,'test'),
  ('7875785879','12345',3,1,'testbx');

/*Table structure for table `tb_user_info` */

DROP TABLE IF EXISTS `tb_user_info`;

CREATE TABLE `tb_user_info` (
  `id` varchar(255) NOT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `e_contact` varchar(255) DEFAULT NULL,
  `e_telephone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `identity` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sex` tinyint(1) NOT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_info` */

/*Table structure for table `tb_user_product` */

DROP TABLE IF EXISTS `tb_user_product`;

CREATE TABLE `tb_user_product` (
  `id` varchar(255) NOT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tb_user_product` */

insert  into `tb_user_product`(`id`,`product_id`,`user_id`) values 
  ('f89957fc4d66e1d1014d6b3f70dc0005','f89957fc4d66e1d1014d6b3f138a0002','5746353'),
  ('f89957fc4d66e1d1014d98da3ad2002a','f89957fc4d66e1d1014d98d30835000c','5746353'),
  ('f89957fc4d66e1d1014d98de6b87002b','f89957fc4d66e1d1014d98d30835000c','7875785879'),
  ('f89957fc4d66e1d1014d98de6b87002c','f89957fc4d66e1d1014d6b3f138a0002','7875785879');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;