USE 'bx';

CREATE TABLE `tb_insured_user` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `identity_type` tinyint(1) NOT NULL,
  `identity` varchar(255) NOT NULL,
  `occupation_type` tinyint(1) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `bd_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `tb_age_group` ADD COLUMN `min_age` int(11) DEFAULT '0',
ADD COLUMN `max_age` int(11) DEFAULT '0';

SET FOREIGN_KEY_CHECKS = 0;

ALTER TABLE `tb_bd_info`  
  ADD CONSTRAINT `fk_product`
  FOREIGN KEY (`product_id`)
  REFERENCES `tb_product` (`id`);

ALTER TABLE `tb_bd_info` 
  ADD CONSTRAINT `fk_user`
  FOREIGN KEY (`user_id`)
  REFERENCES `tb_user` (`id`);

ALTER TABLE `tb_bd_info` ADD COLUMN `user_info_id` VARCHAR(255) NULL  AFTER `product_id` , 
  ADD CONSTRAINT `fk_user_info`
  FOREIGN KEY (`user_info_id`)
  REFERENCES `tb_user_info` (`id`);

UPDATE tb_bd_info dest, (SELECT id, user_id FROM tb_user_info) src 
  SET dest.user_info_id = src.id WHERE dest.user_id=src.user_id;

SET FOREIGN_KEY_CHECKS = 1;

/*
UPDATE tb_bd_info SET excel_addr = CONCAT('/bx', excel_addr)
WHERE excel_addr LIKE '/upload/%';

UPDATE tb_product SET tk_addr = CONCAT('/bx', tk_addr)
WHERE tk_addr LIKE '/upload/%';
*/

UPDATE tb_age_group SET min_age = 1, max_age = 17 WHERE age_group = '1-17';
UPDATE tb_age_group SET min_age = 18, max_age = 80 WHERE age_group = '18-80';
UPDATE tb_age_group SET min_age = 1, max_age = 80 WHERE age_group = '1-80';
  
COMMIT;


