--Here we will put some data to populate the DB at startup - we do it manualy
INSERT INTO public.ad(adid, squaremeters, pagetype, currency) VALUES (1, 2, 'APARTMENT', 'EUR');
INSERT INTO public.ad(adid, squaremeters, pagetype, currency) VALUES (2, 5, 'APARTMENT', 'EUR');

INSERT INTO public.adprice(adid, squaremeterprice, valid, date) VALUES (1, 54, false, current_date);
INSERT INTO public.adprice(adid, squaremeterprice, valid, date) VALUES (2, 88, false, current_date);