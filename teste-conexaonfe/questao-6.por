programa {
	funcao inicio() {
		inteiro totalEleitores
		real votosBrancos, votosNulos, votosValidos, percentualBrancos, percentualNulos, percentualValidos
		
		escreva("Digite o número total de eleitores: ")
		leia(totalEleitores)
		
		escreva("Digite o número de votos brancos: ")
		leia(votosBrancos)
		
		escreva("Digite o número de votos nulos: ")
		leia(votosNulos)
		
		escreva("Digite o número de votos válidos: ")
		leia(votosValidos)
		
		percentualBrancos = (votosBrancos/totalEleitores) * 100
		percentualNulos = (votosNulos/totalEleitores) * 100
		percentualValidos = (votosValidos/totalEleitores) * 100
		
		escreva("% de votos brancos: ", percentualBrancos, "%\n")
		escreva("% de votos nulos: ", percentualNulos, "%\n")
		escreva("% de votos validos: ", percentualValidos, "%\n")
	}
}
