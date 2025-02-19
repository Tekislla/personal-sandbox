package security

import (
	"fmt"
	"net/http"

	"github.com/dgrijalva/jwt-go"
	"github.com/gin-gonic/gin"
)

func VerifyToken() gin.HandlerFunc {
	return func(c *gin.Context) {
		VerifyWebToken(c)
	}
}

func VerifyWebToken(c *gin.Context) {
	SecretKey := "-----BEGIN CERTIFICATE-----\n" +
		"MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApzOi+LMMytH7sg+VMgU7HZGwHEm6au9X2PF2Tf02/jdR/rfLd1l27Bo9vHT/D/udEOGAd+kycHmh6FfmPHNF91Cf24wUEXT5LIiTDVtGrYuVoZZA3QrsW31WYCMs4lb4WOOorx1ttZnThno2dqtWGYUQtqfJ93vUKm7V4qg0f6ex/wxUhPUYRSLUtBusVrNAQUxZoMKVEL49houD8zwEG0SodoDfb2K3eQ7CKKaVK1vil+nwgKO6NAu8uvDavGTCRpOs16X4Cq+AHO0i0RJAObw8cBODAJ4zWct0KV2kDHNYLpiuzG5flui4A2oWJIBVeXTgwwUWKBLvf5Z1SN7EwwIDAQAB" + "\n-----END CERTIFICATE-----"

	reqToken := c.GetHeader("Authorization")

	key, er := jwt.ParseRSAPublicKeyFromPEM([]byte(SecretKey))
	if er != nil {
		fmt.Println(er)
		c.Abort()
		c.Writer.WriteHeader(http.StatusUnauthorized)
		_, _ = c.Writer.Write([]byte("Unauthorized"))
		return
	}

	_, err := jwt.Parse(reqToken, func(token *jwt.Token) (interface{}, error) {
		if _, ok := token.Method.(*jwt.SigningMethodRSA); !ok {
			return nil, fmt.Errorf("unexpected signing method: %v", token.Header["alg"])
		}
		return key, nil
	})

	if err != nil {
		fmt.Println(err)
		c.Abort()
		c.Writer.WriteHeader(http.StatusUnauthorized)
		_, _ = c.Writer.Write([]byte("Unauthorized"))
		return
	}
}
