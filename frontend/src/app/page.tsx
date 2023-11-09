'use client';

import Link from 'next/link';
import { useEffect, useState } from 'react';

type Expense = {
  id: string;
  category: string;
  amount: number;
  date: string;
};

export default function ExpensesPage() {
  const [expenses, setExpenses] = useState<Expense[]>([]);

  useEffect(() => {
    fetch('http://localhost:3000/api/expenses')
      .then((res) => res.json())
      .then((res) => setExpenses(res.reverse()));
  }, []);

  const handleDelete = async (id: string) => {
    if (confirm('Are you sure you want to delete?')) {
      await fetch(`http://localhost:3000/api/expenses/${id}`, {
        method: 'DELETE',
      });

      setExpenses((prev) => prev.filter((a) => a.id !== id));
    }
  };

  const totalExpenses = expenses.reduce(
    (sum, expense) => expense.amount + sum,
    0
  );

  return (
    <div className="p-4 w-full md:w-2/3">
      <div className="flex justify-between">
        <h1 className="text-xl mb-4 font-bold">Expenses</h1>
        <Link href="/expenses/add">
          <button className="border-none rounded-full w-10 h-10 bg-primary-800 text-white shadow-primary-950 shadow">
            +
          </button>
        </Link>
      </div>
      <table className="w-full">
        <thead>
          <tr>
            <th>Date</th>
            <th>Category</th>
            <th>Amount</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((expense) => (
            <tr key={expense.id} className="border-y-2 border-primary-600">
              <td>{expense.date}</td>
              <td>{expense.category}</td>
              <td>
                {expense.amount.toLocaleString('en-US', {
                  style: 'currency',
                  currency: 'SEK',
                })}
              </td>
              <td>
                <button
                  className="border-none text-red-500 bg-transparent"
                  onClick={() => handleDelete(expense.id)}
                >
                  X
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
      <p className="text-xl font-bold mt-4 text-right">
        Total expenses:{' '}
        {totalExpenses.toLocaleString('en-US', {
          style: 'currency',
          currency: 'SEK',
        })}
      </p>
    </div>
  );
}
