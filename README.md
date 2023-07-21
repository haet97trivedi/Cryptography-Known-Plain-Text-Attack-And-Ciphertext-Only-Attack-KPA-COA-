<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h1>Cryptography: Known Plain Text Attack and Ciphertext Only Attack (KPA/COA)</h1>

<h2>Table of Contents</h2>
<ul>
<li><a href="#KPA">1. Known Plain Text Attack (KPA)</a></li>
<li><a href="#COA">2. Ciphertext Only Attack (COA)</a></li>
</ul>

<h2 id="KPA">1. Known Plain Text Attack (KPA)</h2>
<h3>1.1 Result</h3>
<p>Key: captain</p>
<p>Decoded Message: This is the plaintext (task 1) for you HAET NIRAV with the ID number = 27713547437 to decode. 
Good luck!</p>

<h3>1.2 Approach</h3>
<p>The Known Plaintext Attack approach begins with the given ciphertext and the first 2 characters “Th” of the plaintext. The provided decryption method is used to decode messages using all keys present in the password file. The decrypted messages are checked against the known plaintext, and the successful key and its complete decrypted message are stored in a HashMap collection.</p>
<p>The probability of finding a string starting with “Th” was calculated using the binomial distribution, and the results were used to estimate the likelihood of successful decryption.</p>

<h2 id="COA">2. Ciphertext Only Attack (COA)</h2>
<h3>2.1 Result</h3>
<p>Key : mona</p>
<p>Decoded Message: This is the second task (Ciphertext Only Attack) of the assignment of the Cryptography and Secure 
Development course. HAET NIRAV - you are expected to decode it with the assumption that this plaintext is English 
sentences. This plaintext contains this random number 4058 so that you cannot guess it from the others :) Finger-crossed!</p>
<p>The number of ciphertext letters required to decode the encrypted text: 4</p>

<h3>2.2 Approach</h3>
<p>The Ciphertext Only Attack approach uses the n-grams technique to decode the encrypted message. All keys that generate gibberish plaintext are removed from consideration. The decoded messages are checked against a dictionary of common words to generate a score for each key. The key with the highest score is considered to be the most likely to have been used for encryption.</p>
<p>The unicity distance was also calculated to determine the minimum number of ciphertext letters required to be sure that a good, decrypted message is the real one. The actual number of letters required was found to be less than the theoretical calculation due to several factors, such as computational limitations, the quality of the keyspace, and the statistical properties of the plaintext.</p>
</body>
</html>
