
INSERT INTO users
(enabled,username              ,password     , first_name,last_name)
VALUES
(true  , 'test@ramdan.com'   ,'{noop}rahasia', 'Test'    ,'Otika')
;


INSERT INTO authorities
(authority     ,username)
VALUES
('ROLE_USER'  ,'test@ramdan.com')