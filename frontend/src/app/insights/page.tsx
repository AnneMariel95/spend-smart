'use client';
import React, { useState } from 'react';

export default function InsightsPage() {
  const [insights, setInsights] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const generateInsights = () => {
    setIsLoading(true);
    fetch('http://localhost:3000/api/insights')
      .then((res) => res.json())
      .then((res) => setInsights(res.output.completion))
      .finally(() => setIsLoading(false));
  };

  return (
    <div className="mt-4 flex flex-col items-center w-full px-4">
      {insights ? (
        <div
          className="px-4"
          dangerouslySetInnerHTML={{
            __html: insights,
          }}
        />
      ) : (
        <p>Click to generate insights based on your spending</p>
      )}

      <button
        className="bg-primary-700 text-white mt-6 w-48"
        onClick={generateInsights}
        disabled={isLoading}
      >
        {!insights ? 'Get Insights' : 'Regenerate'}
      </button>
      {isLoading && (
        <p className="animate-pulse font-bold text-xl">
          Generating feedback...
        </p>
      )}
    </div>
  );
}
