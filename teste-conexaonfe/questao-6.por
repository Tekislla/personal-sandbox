programa {
	funcao inicio() {
		inteiro totalEleitores
		real votosBrancos, votosNulos, votosValidos, percentualBrancos, percentualNulos, percentualValidos
		
		escreva("Digite o n�mero total de eleitores: ")
		leia(totalEleitores)
		
		escreva("Digite o n�mero de votos brancos: ")
		leia(votosBrancos)
		
		escreva("Digite o n�mero de votos nulos: ")
		leia(votosNulos)
		
		escreva("Digite o n�mero de votos v�lidos: ")
		leia(votosValidos)
		
		percentualBrancos = (votosBrancos/totalEleitores) * 100
		percentualNulos = (votosNulos/totalEleitores) * 100
		percentualValidos = (votosValidos/totalEleitores) * 100
		
		escreva("% de votos brancos: ", percentualBrancos, "%\n")
		escreva("% de votos nulos: ", percentualNulos, "%\n")
		escreva("% de votos validos: ", percentualValidos, "%\n")
	}
}
