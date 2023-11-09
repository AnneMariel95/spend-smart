'use client';
import { useRouter } from 'next/navigation';
import React, { useState } from 'react';

const USER_ID = 'b10c88ac-d34c-4f01-9830-7ae26263fe6b';

export default function AddWalletPage() {
  const [name, setName] = useState('');
  const [balance, setBalance] = useState('');
  const router = useRouter();

  const handleAddWallet = async (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();
    await fetch(`http://localhost:3000/api/wallets`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        name,
        balance,
        userId: USER_ID,
      }),
    });

    router.push('/wallets');
  };
  return (
    <div className="mt-4">
      <h1 className="text-xl mb-4 font-bold">Add Wallet</h1>

      <form className="flex flex-col gap-4" onSubmit={handleAddWallet}>
        <div>
          <label htmlFor="name">Name: </label>
          <input
            id="name"
            type="text"
            value={name}
            placeholder="Enter name"
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <div>
          <label htmlFor="balance">Balance: </label>
          <input
            id="balance"
            type="number"
            placeholder="Enter Balance"
            value={balance}
            onChange={(e) => setBalance(e.target.value)}
          />
        </div>
        <button className="bg-primary-700 text-white mt-6" type="submit">
          Add
        </button>
      </form>
    </div>
  );
}
