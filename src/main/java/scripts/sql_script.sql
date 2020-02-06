CREATE DATABASE task_webservice;
USE task_webservice;

CREATE TABLE `task_webservice`.`users` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `login` VARCHAR(100) NOT NULL,
    `password` VARCHAR(100) NOT NULL,
    `balance` DOUBLE NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC));


INSERT INTO users(login, password, balance) VALUES('111111', 'pwd1', 11.11);
INSERT INTO users(login, password, balance) VALUES('222222', 'pwd2', 22.22);
INSERT INTO users(login, password, balance) VALUES('333333', 'pwd3', 33.33);
