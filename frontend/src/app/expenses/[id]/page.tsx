'use client';

import { useRouter } from 'next/navigation';
import { useEffect, useState } from 'react';

const USER_ID = 'b10c88ac-d34c-4f01-9830-7ae26263fe6b';

export default function AddExpensePage({ params }: { params: { id: string } }) {
  const [category, setCategory] = useState('');
  const [amount, setAmount] = useState('');
  const [date, setDate] = useState('');

  const router = useRouter();

  useEffect(() => {
    fetch(`http://localhost:3000/api/expenses/${params.id}`)
      .then((res) => res.json())
      .then((res) => {
        setCategory(res.category);
        setAmount(res.amount);
        setDate(res.date);
      });
  }, [params.id]);

  const handleUpdateExpense = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await fetch(`http://localhost:3000/api/expenses/${params.id}`, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        date,
        category,
        amount,
        userId: USER_ID,
      }),
    });

    router.push('/');
  };

  return (
    <div className="mt-4">
      <h1 className="text-xl mb-4 font-bold">Update Expense</h1>
      <form className="flex flex-col gap-4" onSubmit={handleUpdateExpense}>
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

        <button className="bg-primary-700 text-white mt-6" type="submit">
          Update
        </button>
      </form>
    </div>
  );
}
