<h1>Trabalho do Grau A de Teoria da Informação - Unisinos</h1>

<h3>Membros do Grupo:</h3>

<p>👩‍🎓 Karolina Pacheco</p>
<p>👩‍🎓 Nadine Schneider</p>
<p>👨‍🎓 Tiago Costa</p>
<p>👨‍🎓 Rafael Stefani Baptista</p>
<hr>
<h3>Objetivo do T1:</h3>

<p>Elaborar uma solução computacional que codifique (compacte) e decodifique (descompacte) arquivos. Para isto deve ser implementado um protótipo que deve ser testado com a compactação e descompactação dos arquivos 📄alice29.txt e 📄sum do corpus de Canterbury (corpus.canterbury.ac.nz/descriptions/#cantrbry).
  
A meta é desenvolver uma solução de compactação (sem perda – lossless) empregando as abordagens de codificação a nível de símbolo. 

As formas de codificação que devem ser suportadas são: 📘Golomb, 📗Elias-Gamma, 📕Fibonacci, 📒Unária e 📙Delta. O usuário deve poder escolher o tipo de compactação que será empregado, bem como o arquivo a ser codificado/decodificado.

A estrutura do processo é a seguinte: a partir da leitura do arquivo original, o encoder gera o arquivo codificado (compactado); o decoder pode então ler o arquivo codificado e gerar um arquivo decodificado, que deve ser exatamente igual ao arquivo original.</p>

<p>No arquivo compactado, os dois primeiros bytes formam um cabeçalho, armazenando meta informação sobre como o arquivo foi codificado: o primeiro byte indica o tipo de codificação (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta) e o segundo byte irá possuir o valor do divisor caso tenha sido usada a codificação Golomb (caso contrário o valor do segundo byte é zero); consequentemente os codewords resultantes do processo de codificação são armazenados no arquivo a partir do terceiro byte.</p>
<hr>
<h4>Observações:</h4>
<p>• A leitura e gravação dos arquivos pode ser realizada byte a byte.</p>

<p>• O processo de geração do arquivo compactado pode implicar no uso de um buffer de saída onde os codewords (resultantes da codificação do arquivo) irão sendo inseridos um a um, empregando-se para isto operações bit a bit (bitwise ops). Finalmente, o conteúdo do buffer de saída é então gravado em um arquivo.</p>

<p>• O encoder e o decoder podem ser na realidade o mesmo programa, sendo somente parametrizado de forma distinta para cada caso (um parâmetro pode ser o modo de operação: codificar ou decodificar).</p>

<hr>
<h4>🛠 TBD ⚒</h4>

<p>(✔) Menu</p>
<p>(✔) Leitura</p>
<p>(✔) Escrita</p>
<p>(✔) Unária</p> 
<p>📙Delta</p>
<p>📕Fibonacci</p>
<p>📗Elias-Gamma</p>
<p>📘Golomb</p>

<hr>
<h4>Referências</h4>
https://www.geeksforgeeks.org/bitwise-operators-in-java/
<hr>
