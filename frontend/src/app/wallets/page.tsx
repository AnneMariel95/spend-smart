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
      <div className="flex justify-between px-5">
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
              <p>SEK {wallet.balance}</p>
            </div>
            <button
              className="bg-primary-700 text-white border-none shadow shadow-primary-900"
              onClick={() => handleDelete(wallet.id)}
            >
              Delete
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
