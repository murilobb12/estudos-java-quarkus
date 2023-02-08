USE quarkus;
DROP TABLE IF EXISTS Cliente;
CREATE TABLE `Cliente` (
	`id` int NOT NULL AUTO_INCREMENT,
	`nome` VARCHAR(50) NOT NULL,
	`cpf` VARCHAR(14) NOT NULL,
	PRIMARY KEY(`id`)) ENGINE=InnoDB;

DROP TABLE IF EXISTS Conta;
CREATE TABLE `Conta` (
	`id` int NOT NULL AUTO_INCREMENT,
	`numero_conta` VARCHAR(20) NOT NULL,
	`saldo` decimal(15,2) NOT NULL,
	`cliente_id` int NOT NULL,
	PRIMARY KEY (`id`),
	FOREIGN KEY (`cliente_id`) REFERENCES `Cliente` (`id`)) ENGINE=InnoDB;