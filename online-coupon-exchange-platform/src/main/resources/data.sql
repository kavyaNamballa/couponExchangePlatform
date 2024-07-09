INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('ADMIN',CURDATE(),'DBA');

INSERT INTO `roles` (`role_name`,`created_at`, `created_by`)
  VALUES ('USER',CURDATE(),'DBA');

INSERT INTO `brands` VALUES
(1,'amazon',"https://i.pinimg.com/564x/5a/62/70/5a62706bc5603694b1bd08acc40d3096.jpg"),
(2,'flipkart',"https://i.pinimg.com/736x/b9/31/28/b931287eff4265de219421b7837ff9e9.jpg"),
(3,'swiggy',"https://i.pinimg.com/564x/b3/8a/a1/b38aa1b21050b0e769a97eb751d12829.jpg"),
(4,'myntra',"https://i.pinimg.com/564x/13/49/0c/13490c062140a1a5eee5c74de39255dd.jpg"),
(5,'ajio',"https://i.pinimg.com/736x/5d/b3/6a/5db36abf85ec25861691377718588d9b.jpg"),
(6,'boat',"https://i.pinimg.com/736x/08/16/15/081615c30f30318974ce916da17b88e3.jpg"),
(7,'phonepe',"https://i.pinimg.com/564x/b1/5d/fc/b15dfc9f11992f147703c4b1ff45ea8a.jpg"),
(8,'uber',"https://i.pinimg.com/564x/f3/b7/ac/f3b7acbc81e268b28ffc2371c555c6d4.jpg"),
(9,'paytm',"https://i.pinimg.com/564x/d2/3c/bf/d23cbf926d0672238d35ff3486a8c7a3.jpg"),
(10,'redbus',"https://i.pinimg.com/564x/2e/ba/5b/2eba5b05e132ff093eb4e64c77f1c55a.jpg"),
(11,'dermaco',"https://images.thedermaco.com/TheDermaCoLogo2-min.png"),
(12,'makemytrip',"https://cdn.grabon.in/gograbon/images/merchant/1620803829927/makemytrip-logo.jpg");

INSERT INTO `person` (`name`,`email`,`mobile_number`,`pwd`,`role_id`,`created_at`, `created_by`)
  VALUES ('Admin','squad@gmail.com','7854907303','$2a$10$XhU4UcSxDPb5G0I0fT/CZ.Lfj2VW2fkLkUP5cOEM.xM8EzyUQXaD2', 1 ,CURDATE(),'DBA');

INSERT INTO `coupon` (`code`, `coupon_type`, `discount_value`, `expiry_date`, `min_purchase_amount`, `description`,`person_id`,`brand_id`) VALUES
('SAVE20', 'Percentage Discount Coupon', '20%', '2024-06-30', 50, '20% off on purchases above $50',1,1),
('GET10', 'Fixed Amount Discount Coupon', '$10', '2024-06-30', 50, '$10 off on purchases above $50',1,2),
('FREESHIP', 'Free Shipping Coupon', 'Free shipping', '2024-06-30', 30, 'Free shipping on purchases above $30',1,3),
('FIRSTBUY', 'First-Time User Coupon', '15%', '2024-12-31', 0, '15% off for first-time users',1,4),
('BOGO', 'Buy One Get One Free (BOGO) Coupon', 'BOGO', '2024-09-30', 0, 'Buy one get one free on select items',1,5),

('AMAZON50', 'Fixed Amount Discount Coupon', '$50', '2024-07-31', 200, '$50 off on purchases above $200',1,1),
('FLIP20', 'Percentage Discount Coupon', '20%', '2024-06-30', 100, '20% off on purchases above $100',1,2),
('MEESHO10', 'Fixed Amount Discount Coupon', '$10', '2024-08-31', 40, '$10 off on purchases above $40',1,3),
('MYNTRA30', 'Percentage Discount Coupon', '30%', '2024-06-30', 150, '30% off on purchases above $150',1,4),
('AJIO5', 'Fixed Amount Discount Coupon', '$5', '2024-06-30', 30, '$5 off on purchases above $30',1,5),

('SAVE30', 'Percentage Discount Coupon', '30%', '2024-11-30', 70, '30% off on purchases above $70',1,1),
('FREESHIPFLIP', 'Free Shipping Coupon', 'Free shipping', '2024-10-31', 50, 'Free shipping on purchases above $50',1,2),
('NEWMEESHO', 'First-Time User Coupon', '20%', '2024-12-31', 0, '20% off for first-time users',1,3),
('MYNTRA5', 'Fixed Amount Discount Coupon', '$5', '2024-06-30', 20, '$5 off on purchases above $20',1,4),
('FREESHIPAJIO', 'Free Shipping Coupon', 'Free shipping', '2024-08-31', 40, 'Free shipping on purchases above $40',1,5),

('BOGOAMAZON', 'Buy One Get One Free (BOGO) Coupon', 'BOGO', '2024-12-31', 0, 'Buy one get one free on select items',1,1),
('FIRSTFLIP', 'First-Time User Coupon', '10%', '2024-12-31', 0, '10% off for first-time users',1,2),
('BOGOMEESHO', 'Buy One Get One Free (BOGO) Coupon', 'BOGO', '2024-09-30', 0, 'Buy one get one free on select items',1,3),
('MYNTRA15', 'Percentage Discount Coupon', '15%', '2024-07-31', 60, '15% off on purchases above $60',1,4),
('SAVE25AJIO', 'Percentage Discount Coupon', '25%', '2024-11-30', 80, '25% off on purchases above $80',1,5);


--MYNTRA COUPONS

INSERT INTO coupon (code, coupon_type, discount_value, expiry_date, min_purchase_amount, description,person_id,brand_id) VALUES

('8PTM224JULUA83FCHDUG', 'Percentage Discount Coupon', '15%', '2024-08-31', 1999, 'Get Flat 15% Extra off upto Rs.400 on top of platform discounts on min purchase of 1999/- on the Myntra app or website',1,4),

('8PPDRMAYHVSUE7W34Y', 'First-Time User Coupon', '$400', '2024-07-31', 1799, 'Get Flat $400 Extra off on a minimum cart value of $1799 on Myntra app/website The maximum discount per order is 400/-',1,4),

('HOBHOMEDECORS', 'Percentage Discount Coupon', '5%', '2024-12-31', 0, '5% off the orders',1,4),
('BUY2GET10BUY3GET15', 'MIN PURCHASE OF 3 ITEMS', '15%', '2025-01-01', 0, '15% OFF ON MIN Purchase of 3 items',1,4),

('MYNTRA300', 'FLAT 300/- off on min purchase of 1499/-', '300/-', '2024-09-30', 0, 'Flat 300/- off on min purchase of 1499/-',1,4);

--AJIO COUPONS

INSERT INTO coupon (code, coupon_type, discount_value, expiry_date, min_purchase_amount, description,person_id,brand_id) VALUES

('IJF8QKET8GJDH20', 'Percentage Discount Coupon', '50%', '2024-07-31', 1499, 'Min 50% off + Flat 250/- Extra off + Free Shipping on AJIO Deals worth your while! Shop now at one stop destination for the best of styles on AJIO till 10th August 2024 using this this coupon.Hurry ,use this coupon and grab your favorites from 6,00,000+ curated styles of T-shirts ,Jackets,Sweaters and SweatShirts,Jeans & Trousers,Dresses,Sneakers & Footwear,Accessories,Jewelry, & much more from top brands',1,5),

('SALE70', 'Percentage Discount Coupon', '70%', '2024-08-31', 30, 'Get 70% Off on Orders Worth ₹1290 or More. Shop from Red tape, jack and Jones, Vero Moda, Fila and more. Maximum discount offered ₹3000. How to avail Deal : 1. Click on Get Deal 2. Purchase discounted products from the landing page.',1,5),

('FREESHIP', 'First-Time User Coupon', 'Free Shipping', '2024-12-31', 0, 'Get Free Shipping on your First Purchase. Valid for New user. Applicable on all payment methods. Click on Shop Now or Get Deal. Purchase discounted products from the landing page.',1,5);

INSERT INTO coupon (coupon_type, discount_value, expiry_date, min_purchase_amount, description,person_id,brand_id,shop_now) VALUES

('Percentage Discount Coupon', '50%', '2024-08-31', 0, '50-90% Off On Fashion Shop during the Giant Fashion Sale and get 50-90% Off On 6000+ Top Fashion Brands. No coupon code required to avail this offer. Get extra discounts with select bank partners. Sale starts 5th July 2024.',1,5,"https://www.ajio.com/?utm_source=Admitad&utm_medium=affiliate&utm_campaign=335261_CU9689cf189c621c&clickid=668a679ca789710001d48474&pid=11&offer_id=2&attribution_window=1D&return_cancellation_window=45D");


--Boat

INSERT INTO coupon (coupon_type, discount_value, expiry_date, min_purchase_amount, description,person_id,brand_id,shop_now) VALUES

('Percentage Discount Coupon', '60%', '2024-08-31', 0, 'Get upto 60% off on boAt audio products like headphone, earphones, speakers, soundbars and more + free gifts on your purchases. How to avail Boat offers : 1. Click on Get Deal 2. Purchase discounted products from the landing page.',1,6,"https://www.boat-lifestyle.com/?utm_source=Affise_Admitad&clickid=668a6a26ad55ae00015b3ec5"),

('Percentage Discount Coupon', '75%', '2024-08-31', 0, '75% Off Sitewide Sale: Get Upto 75% Off Across All Categories.',1,6,"https://www.boat-lifestyle.com/?utm_source=Affise_Admitad&clickid=668a6a26ad55ae00015b3ec5");

INSERT INTO coupon (code, coupon_type, discount_value, expiry_date, min_purchase_amount, description,person_id,brand_id) VALUES

('FLASH500', 'Fixed Amount Discount Coupon', '500/-', '2024-08-31', 0, 'Get flat ₹500 discount on select products. Use coupon code to avail this offer. Valid only on products listed on the landing page.',1,6),

('SUMMER500', 'Fixed Amount Discount Coupon', '500/-', '2024-08-31', 0, 'Get ₹500 off on select products from various categories like headphones, speakers, soundbars and music equipments. Use coupon code to avail this offer.',1,6);


INSERT INTO coupon (code, coupon_type, discount_value,min_purchase_amount, description,person_id,brand_id) VALUES
('FIRSTBUY', 'Percentage Discount Coupon', '500 Off', 1490, 'Get Upto Extra 30% Off on Orders of ₹1490 & Above (First 2 Purchases).', 1, 5),
('AGHDFC', 'Percentage Discount Coupon', '50% Off', 2000, 'Get Upto 50% Off + Extra ₹2000 Off Via HDFC Cards (Ajio Gold Members)', 1, 5),
('PREMIYUM', 'Percentage Discount Coupon', '15% Off Upto ₹2300', 4999, 'Get extra upto 15% off upto ₹2300 on select premium products.', 1, 5),
('Coupon Activate...', 'Percentage Discount Coupon', '500 Off', 1490, 'Get Upto 80% Across Categories Like Fashion, Home And More.', 1, 1),
('FLYFK', 'Percentage Discount Coupon', 'Up To 15%', 1490, 'Enjoy Up To 15% Off Domestic & International Flights With Code', 1, 2),
('FLYBSD', 'Percentage Discount Coupon', '10% off', 1490, 'Save 10% on Hdfc Bank Credit Cards', 1, 2),
('FLYBDS', 'Percentage Discount Coupon', '16% Off', 1490, 'Take Up to 16% Off with Coupon', 1, 2),
('MS35Dsjrw', 'Percentage Discount Coupon', '35% off', 10000, 'Save 35% Off using Coupon Code', 1, 2),
('TAVLOUF36896', 'Percentage Discount Coupon', '20% Off', 999, 'Get Up To 20% Off + Free Delivery With Meesho New User Offer', 1, 3),
('PBHUMWD64219', 'Percentage Discount Coupon', '65% Off', 20000, "Today's Top Deal: Fashion, Electronics, Home & More Up To 65% Off", 1, 3);


