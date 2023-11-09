'use client';

import { Wallet } from '@/app/wallets/page';
import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';

const USER_ID = 'b10c88ac-d34c-4f01-9830-7ae26263fe6b';

export default function AddExpensePage() {
  const [category, setCategory] = useState('');
  const [amount, setAmount] = useState('');
  const [date, setDate] = useState('');
  const [walletId, setWalletId] = useState('');

  const [wallets, setWallets] = useState<Wallet[]>([]);
  const router = useRouter();

  useEffect(() => {
    fetch('http://localhost:3000/api/wallets')
      .then((res) => res.json())
      .then((res) => {
        setWallets(res);
        setWalletId(res[0].id);
      });
  }, []);

  const handleAddExpense = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await fetch(`http://localhost:3000/api/expenses`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        date,
        category,
        amount,
        walletId,
        userId: USER_ID,
      }),
    });

    router.push('/');
  };

  return (
    <div className="mt-4">
      <h1 className="text-xl mb-4 font-bold">Add Expense</h1>
      <form className="flex flex-col gap-4" onSubmit={handleAddExpense}>
        <div className="form-field">
          <label htmlFor="date">Date: </label>
          <input
            id="date"
            required
            type="date"
            value={date}
            onChange={(e) => setDate(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="category">Category: </label>
          <input
            id="category"
            required
            type="text"
            list="expenseCategories"
            placeholder="Enter Category"
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          />
          <datalist id="expenseCategories">
            <option value="Food" />
            <option value="Transportation" />
            <option value="Housing" />
            <option value="Utilities" />
            <option value="Entertainment" />
            <option value="Clothing" />
            <option value="Healthcare" />
          </datalist>
        </div>
        <div>
          <label htmlFor="amount">Amount: </label>
          <input
            required
            id="amount"
            type="number"
            min="1"
            placeholder="Enter Amount"
            value={amount}
            onChange={(e) => setAmount(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="wallet">Wallet: </label>
          <select
            required
            id="wallet"
            value={walletId}
            onChange={(e) => setWalletId(e.target.value)}
          >
            {wallets.map((wallet) => (
              <option key={wallet.id} value={wallet.id}>
                {wallet.name}
              </option>
            ))}
          </select>
        </div>
        <button className="bg-primary-700 text-white mt-6" type="submit">
          Add
        </button>
      </form>
    </div>
  );
}
