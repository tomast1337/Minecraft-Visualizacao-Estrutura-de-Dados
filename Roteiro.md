# Trabalho insertion sort
Neste trabalho irei utilizar o jogo Minecraft e Ovelhas, representando os elementos e esse cercado representando a memória.

{mostrar ovelhas e cercado}

As ovelhas possuem cada uma um nome de, 1 a 16, uma cor única também representando seu valor, no caso as cores estão em ordem de matiz.

{mostrar ovelhas com cores e nomes}

O código dessa visualização está disponível no meu github.

## Como ele funciona?
### Principais características
O Insertion Sort é um algoritmo de ordenação com complexidade quadrada, que funciona comparando todos os elementos de uma array a todos os outros que estão em posições anteriores a ele e é inserido entre os dois elementos onde um deles é maior é o outro é menor formado assim de elemento em elemento um array ordenada.
O insertion sort é simples de ser implementado pois é muito próximo de um senso comum sobre ordenação, talvez usado por muitos, se não todos, nós ao organizar figurinhas cartas e outras coisas .


### Visualização
Vamos agora a visualização do insertion sort.

{Mostrar vídeo o insertion sort}


### Olhando mais de perto
Vamos olhar mais devagar para entender o'que acontece 

{Mostrar vídeo o insertion sort mais lento e narrando o que acontece}


### Qual a diferença do entre  os algoritmos Insertion Sort e Bubble Sort and Selection Sort?

TODO:

### Apresentar o chinês usando como base o array 15, 8, 4, 10, 6 utilizado no slide Ordenação

Agora, vamos mostrar como o insertion sort funciona passo a passo. Aqui nós temos uma implementação do algoritmo em C++, que reproduz, de uma forma simplificada, o mesmo procedimento que nós vimos com as ovelhas do Minecraft.

Ao executarmos o programa, nosso ponto de entrada é a função main. Aqui nós definimos um inteiro "tamanho" com o valor 5, e "a", a sequência de elementos que queremos ordenar, um vetor de inteiros contendo os números 15, 8, 4, 10 e 6.

Definidas essas variáveis, nós chamamos a função insertion_sort(), que irá ordenar esse vetor. Nós passamos como parâmetros o vetor a ser ordenado, e o número de elementos (ou tamanho do vetor), que recebe o nome local "fim".

Precisamos, então, percorrer cada elemento do vetor e inseri-los na posição correta. Para isso, criamos um loop "for" usando a variável de controle "i", que vai da posição 2 até a última posição do vetor. Lembrando que nós não precisamos verificar o primeiro elemento, já que ele não pode ser deslocado para a esquerda. Por isso nossa iteração começa na segunda posição.

Para cada um desses elementos, devemos compará-lo com todos os elementos anteriores e determinar se ele deve ou não ser trocado. Para isso, criamos outro loop "for", usando a variável de controle "j", que irá do valor de i que tínhamos anteriormente, ou seja, da posição do elemento que estamos verificando, até a posição 0. Ou seja, vamos percorrer todos os elementos anteriores, da direita para a esquerda, terminando no início do vetor. Observe que nós não verificamos elementos DEPOIS do "i", já que eles serão ordenados na próxima iteração do primeiro "for".

Na próxima linha, verificamos se o valor do elemento anterior, na posição j-1, é maior do que o elemento atual, j. Se esse for o caso, nós devemos trazê-lo para o começo, trocando os elementos de lugar. Para isso, definimos uma váriavel auxiliar, "aux", para o valor na posição anterior. Isso é para não perdermos esse valor quando o substuírmos pelo atual. Nós então alteramos o valor anterior para o atual, e, finalmente, definimos o valor atual para o valor de "aux", que contém o valor do elemento anterior.

Esse processo se repete até chegarmos ao início do vetor. Dessa forma, os menores elementos serão sempre trazidos para o começo da lista.

Ao terminarmos o segundo loop "for", voltamos ao começo do primeiro. Note que o valor de i agora será 2, ou seja, nós iremos comparar o terceiro elemento do vetor, contra o segundo e o primeiro.

<pausa>

Dessa forma, o 4, que é menor que 15 e 8, vai para o começo da lista.

Esse processo se repete, até chegarmos ao último elemento.

<pausa>

Ao final desse processo, temos todos os elementos ordenados. Voltamos à função main, e retornamos 0.

O chinês dessa função fica dessa forma:

[mostrar o chinês]

### Opnião sobre os algoritmo 

Na minha opinião o algoritmo mais fácil de implementar é o Bubble Sort, apesar da ideia dele não ser muito poxima do "senso comum" ele é muito curto TODO: Melhor explicar.

O mais eficiente é o Insertion Sort TODO
