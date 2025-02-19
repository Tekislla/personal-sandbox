programa {

	inclua biblioteca Matematica --> mat
	
	funcao inicio() {
		real nota1, nota2, nota3, media
		
		escreva("Digite a primeira nota: ")
		leia(nota1)

		escreva("Digite a segunda nota: ")
		leia(nota2)

		escreva("Digite a terceira nota: ")
		leia(nota3)

		media = (nota1 + nota2 + nota3)/3
		
		
		se (media >= 7) {
        		escreva("Você foi aprovado com média ", mat.arredondar(media, 2))
		} senao {
			escreva("Você foi reprovado com média ", mat.arredondar(media, 2))
		}
	}
}
