-- Sample users

INSERT INTO user ( id, email, password, enabled, creation_date, first_name, last_name)  VALUES ( 0, 'javi.more.garc@gmail.com', '$2a$10$4xQwdr3NYc.MiIKr4w2feOh2DhYfJJ/HpMnxr65CBLOLn3KD2rKj6', true, now(), 'Javier', 'Moreno' );

-- Sample voice files

INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -5, 0, 'My filename -5', 'ERROR', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -4, 0, 'My filename -4', 'SENT', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -3, 0, 'My filename -3', 'PROCESSING', '1345Af', now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -2, 0, 'My filename -2', 'ERROR', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -1, 0, 'My filename -1', 'MACHINECOMPLETE', '45MHD', now());

