DELIMITER $$

CREATE TRIGGER calculate_total_price
BEFORE INSERT ON Purchased
FOR EACH ROW
BEGIN
    SET NEW.total_price = NEW.quantity * NEW.unit_price;
END $$

DELIMITER ;
