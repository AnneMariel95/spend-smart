TRUNCATE public.users CASCADE;
TRUNCATE public.wallets CASCADE;
-- TRUNCATE public.expenses;

INSERT INTO public.users(id, first_name, last_name) VALUES ('b10c88ac-d34c-4f01-9830-7ae26263fe6b', 'Mariel', 'Catapang');

INSERT INTO public.wallets(id, balance, name, user_id) VALUES ('3828a6db-f6da-41ae-9452-11c60dceb3a0', 20000 , 'SEB', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');
INSERT INTO public.wallets(id, balance, name, user_id) VALUES ('4568a6db-e6da-51ae-8452-22c60dce8910', 1000, 'Wise', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');


INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 53, 'Food', '2023-11-07', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 50, 'Food', '2023-10-01', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 75, 'Transportation', '2023-10-02', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 100, 'Shopping', '2023-10-03', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 40, 'Entertainment', '2023-10-04', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 90, 'Dining Out', '2023-10-05', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 60, 'Groceries', '2023-10-06', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 80, 'Utilities', '2023-10-07', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 55, 'Food', '2023-10-08', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 70, 'Transportation', '2023-10-09', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 120, 'Shopping', '2023-10-10', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 45, 'Entertainment', '2023-10-11', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 85, 'Dining Out', '2023-10-12', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 65, 'Groceries', '2023-10-13', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 95, 'Utilities', '2023-10-14', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 60, 'Food', '2023-10-15', '3828a6db-f6da-41ae-9452-11c60dceb3a0', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');

INSERT INTO expenses (id, amount, category, date, wallet_id, user_id)
VALUES (gen_random_uuid(), 80, 'Transportation', '2023-10-16', '4568a6db-e6da-51ae-8452-22c60dce8910', 'b10c88ac-d34c-4f01-9830-7ae26263fe6b');