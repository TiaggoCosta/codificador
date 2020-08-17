Trabalho do Grau A de Teoria da Informação - Unisinos

Membros do Grupo:

👩‍🎓 Karolina Pacheco
👩‍🎓 Nadine Schneider
👨‍🎓 Tiago Costa

Objetivo do T1:

Elaborar uma solução computacional que codifique (compacte) e decodifique (descompacte) arquivos. Para isto deve ser implementado um protótipo (usando a linguagem de sua escolha) que deve ser testado com a compactação e descompactação dos arquivos 📄alice29.txt e 📄sum do corpus de Canterbury (corpus.canterbury.ac.nz/descriptions/#cantrbry), disponíveis na pasta junto ao enunciado no moodle.
A meta é desenvolver uma solução de compactação (sem perda – lossless) empregando as abordagens de codificação a nível de símbolo. As formas de codificação que devem ser suportadas são: 📘Golomb, 📗Elias-Gamma, 📕Fibonacci, 📒Unária e 📙Delta. O usuário deve poder escolher o tipo de compactação que será empregado, bem como o arquivo a ser codificado/decodificado.
A figura a seguir apresenta a estrutura do processo: a partir da leitura do arquivo original, o encoder gera o arquivo codificado (compactado); o decoder pode então ler o arquivo codificado e gerar um arquivo decodificado, que deve ser exatamente igual ao arquivo original.

No arquivo compactado, os dois primeiros bytes formam um cabeçalho, armazenando meta informação sobre como o arquivo foi codificado: o primeiro byte indica o tipo de codificação (0: Golomb, 1:Elias-Gamma, 2:Fibonacci, 3:Unária e 4:Delta) e o segundo byte irá possuir o valor do divisor caso tenha sido usada a codificação Golomb (caso contrário o valor do segundo byte é zero); consequentemente os codewords resultantes do processo de codificação são armazenados no arquivo a partir do terceiro byte.

Observações:
• A leitura e gravação dos arquivos pode ser realizada byte a byte.

• O processo de geração do arquivo compactado pode implicar no uso de um buffer de saída onde os codewords (resultantes da codificação do arquivo) irão sendo inseridos um a um, empregando-se para isto operações bit a bit (bitwise ops). Finalmente, o conteúdo do buffer de saída é então gravado em um arquivo.

• O encoder e o decoder podem ser na realidade o mesmo programa, sendo somente parametrizado de forma distinta para cada caso (um parâmetro pode ser o modo de operação: codificar ou decodificar).

🛠 TBD
Leitura
Escrita
Menu
📒Unária 
📙Delta
📕Fibonacci
📗Elias-Gamma
📘Golomb