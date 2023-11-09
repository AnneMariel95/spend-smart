'use client';

import Link from 'next/link';
import React, { useEffect, useState } from 'react';

export type Wallet = {
  id: string;
  name: string;
  balance: number;
};

export default function WalletsPage() {
  const [wallets, setWallets] = useState<Wallet[]>([]);

  useEffect(() => {
    fetch('http://localhost:3000/api/wallets')
      .then((res) => res.json())
      .then((res) => setWallets(res));
  }, []);

  const totalBalance = wallets.reduce((sum, wallet) => wallet.balance + sum, 0);

  const handleDelete = async (id: string) => {
    if (confirm('Are you sure you want to delete?')) {
      const response = await fetch(`http://localhost:3000/api/wallets/${id}`, {
        method: 'DELETE',
      });

      if (response.ok) {
        setWallets((prev) => prev.filter((wallet) => wallet.id !== id));
      } else {
        alert('Cannot delete wallet with associated expenses');
      }
    }
  };

  return (
    <div className="p-4 w-full">
      <div className="flex justify-between">
        <h1 className="text-xl mb-4 font-bold">Wallets</h1>
        <Link href="/wallets/add">
          <button className="border-none rounded-full w-10 h-10 bg-primary-800 text-white shadow-primary-950 shadow">
            +
          </button>
        </Link>
      </div>{' '}
      <div className="flex flex-col gap-6 p-2 items-center">
        {wallets.map((wallet) => (
          <div
            key={wallet.id}
            className="shadow-primary-900 shadow bg-white py-2 px-4 flex justify-between w-full lg:w-[40rem] rounded"
          >
            <div>
              <p>{wallet.name}</p>
              <p>
                {wallet.balance.toLocaleString('en-US', {
                  style: 'currency',
                  currency: 'SEK',
                })}
              </p>
            </div>
            <button
              className="text-red-500 border-none"
              onClick={() => handleDelete(wallet.id)}
            >
              X
            </button>
          </div>
        ))}
        <p className="text-xl font-bold mt-4">
          Total balance:{' '}
          {totalBalance.toLocaleString('en-US', {
            style: 'currency',
            currency: 'SEK',
          })}
        </p>
      </div>
    </div>
  );
}
