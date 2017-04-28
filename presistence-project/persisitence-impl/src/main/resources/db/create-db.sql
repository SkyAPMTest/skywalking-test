CREATE TABLE IF NOT EXISTS `cache_table` (
  `id`    INT         NOT NULL AUTO_INCREMENT,
  `CACHE_KEY`   VARCHAR(30) NOT NULL,
  `CACHE_VALUE` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`)
);


DELETE FROM `cache_table`
WHERE id = '1';

INSERT INTO `cache_table` (`id`, `CACHE_KEY`, `CACHE_VALUE`) VALUES (1, "test", "test");
