INSERT INTO `coins` (`id`, `code`, `name`) VALUES (NULL, 'USD', 'Dolares'), (NULL, 'EUR', 'Euros');
INSERT INTO `rates` (`id`, `description`, `name`) VALUES (NULL, 'tasa del banco central de venezuela', 'BCV'); 
INSERT INTO `exchange` (`id`, `coin_id`, `rate_id`, `value`, `previous_value`,  `update_at`) VALUES (NULL, 1, 1, 0.00, 0.00, NOW()), (NULL, 2, 1, 0.00, 0.00, NOW());
