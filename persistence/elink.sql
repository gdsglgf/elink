-- phpMyAdmin SQL Dump
-- version 4.1.6
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 2016-12-21 09:19:23
-- 服务器版本： 5.6.16
-- PHP Version: 5.5.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `elink`
--

-- --------------------------------------------------------

--
-- 表的结构 `t_batch`
--

CREATE TABLE IF NOT EXISTS `t_batch` (
  `batch_id` int(10) NOT NULL,
  `batch_name` varchar(32) NOT NULL,
  PRIMARY KEY (`batch_id`),
  UNIQUE KEY `batch_name` (`batch_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_batch`
--

INSERT INTO `t_batch` (`batch_id`, `batch_name`) VALUES
(0, 'part-m-00000.bz2');

-- --------------------------------------------------------

--
-- 表的结构 `t_directory`
--

CREATE TABLE IF NOT EXISTS `t_directory` (
  `dir_id` int(10) NOT NULL,
  `dir_name` varchar(32) NOT NULL,
  PRIMARY KEY (`dir_id`),
  UNIQUE KEY `dir_name` (`dir_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- 转存表中的数据 `t_directory`
--

INSERT INTO `t_directory` (`dir_id`, `dir_name`) VALUES
(1, 'sogout_data.1.comp');

-- --------------------------------------------------------

--
-- 表的结构 `t_html`
--

CREATE TABLE IF NOT EXISTS `t_html` (
  `html_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `docno` char(66) NOT NULL,
  `url` text NOT NULL,
  `title` text NOT NULL,
  `keywords` text NOT NULL,
  `description` text NOT NULL,
  `loc_id` bigint(20) unsigned NOT NULL,
  `rank` int(10) DEFAULT NULL,
  PRIMARY KEY (`html_id`),
  UNIQUE KEY `docno` (`docno`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_html`
--

INSERT INTO `t_html` (`html_id`, `docno`, `url`, `title`, `keywords`, `description`, `loc_id`, `rank`) VALUES
(1, '0015e77674b7604f-07dd0ee2a1c1ef92-469b9cc3ce78f64d392776be46976e8f', 'http://www.test.com/', '一千零一夜（古代阿拉伯民间故事集）', '一千零一夜 天方夜谭 阿拉伯之夜', '《一千零一夜》（tales from the thousand and one nights）是阿拉伯民间故事集，又名《天方夜谭》。相传古代印度与中国之间有一萨桑国，国王山鲁亚尔生性残暴嫉妒，因王后行为不端，将其杀死，此后每日娶一少女，翌日晨即杀掉，以示报复。宰相的女儿山鲁佐德为拯救无辜的女子，自愿嫁给国王，用讲述故事方法吸引国王，每夜讲到最精彩处，天刚好亮了，使国王爱不忍杀，允她下一夜继续讲。她的故事一直讲了一千零一夜，国王终于被感动，与她白首偕老。因其内容丰富，规模宏大，故被高尔基誉为世界民间文学史上“最壮丽的一座纪念碑”。', 1, 1);

-- --------------------------------------------------------

--
-- 表的结构 `t_location`
--

CREATE TABLE IF NOT EXISTS `t_location` (
  `loc_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `disk_id` int(10) DEFAULT NULL,
  `dir_id` int(10) DEFAULT NULL,
  `batch_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_location`
--

INSERT INTO `t_location` (`loc_id`, `disk_id`, `dir_id`, `batch_id`) VALUES
(1, 1, 1, 0);

-- --------------------------------------------------------

--
-- 表的结构 `t_parser_log`
--

CREATE TABLE IF NOT EXISTS `t_parser_log` (
  `log_id` int(10) NOT NULL AUTO_INCREMENT,
  `loc_id` bigint(20) unsigned NOT NULL,
  `html_amount` int(10) DEFAULT NULL,
  `cost_time` int(10) DEFAULT NULL,
  PRIMARY KEY (`log_id`),
  UNIQUE KEY `loc_id` (`loc_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- 转存表中的数据 `t_parser_log`
--

INSERT INTO `t_parser_log` (`log_id`, `loc_id`, `html_amount`, `cost_time`) VALUES
(1, 1, 26571, 183891);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



ALTER TABLE t_html AUTO_INCREMENT=2;
ALTER TABLE t_location AUTO_INCREMENT=2;
ALTER TABLE t_parser_log AUTO_INCREMENT=2;

SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name="t_html";
SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name="t_location";
SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name="t_parser_log";

