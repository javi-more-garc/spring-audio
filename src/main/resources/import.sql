-- Sample users

INSERT INTO user ( id, email, password, enabled, creation_date, first_name, last_name)  VALUES ( 0, 'javi.more.garc@gmail.com', '$2a$10$4xQwdr3NYc.MiIKr4w2feOh2DhYfJJ/HpMnxr65CBLOLn3KD2rKj6', true, now(), 'Javier', 'Moreno' );

-- Sample voice files

INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -4, 0, 'My filename -5', 'ERROR', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -3, 0, 'My filename -4', 'SENT', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -2, 0, 'My filename -3', 'PROCESSING', '1345Af', now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( -1, 0, 'My filename -2', 'ERROR', null, now());
INSERT INTO voice_file ( id, user_id, filename, status, media_id, creation_date)  VALUES ( 0, 0, 'My filename -1', 'MACHINECOMPLETE', '45MHD', now());

-- Sample voice file keywords

INSERT INTO voice_file_keyword ( id, user_id, voice_file_id, name, unknowns, creation_date)  VALUES ( -1, 0, 0, 'conference', '9.65,13.27', now());
INSERT INTO voice_file_keyword ( id, user_id, voice_file_id, name, unknowns, creation_date)  VALUES ( -9, 0, 0, 'students', '35.76,71,80.32,99.9,141.7', now());



