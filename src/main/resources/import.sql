INSERT INTO `coins` (`id`, `code`, `name`) VALUES (NULL, 'USD', 'Dolares'), (NULL, 'EUR', 'Euros'), (NULL, 'CNY', 'Yuanes'), (NULL, 'TRY', 'Liras Turcas'), (NULL,'RUB', 'Rublos');
INSERT INTO `rates` (`id`, `description`, `name`) VALUES (NULL, 'tasa del banco central de venezuela', 'BCV'); 

INSERT INTO `exchange` (`previous_value`, `value`, `coin_id`, `create_at`, `id`, `rate_id`) VALUES ('', '0', '1', '2025-11-10 14:56:14.000000', NULL, '1'), ('', '0', '2', '2025-11-10 14:56:14.000000', NULL, '1') 