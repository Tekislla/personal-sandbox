package routes

import (
	"example/controllers"
	"example/security"
	"time"

	"github.com/gin-contrib/cors"
	"github.com/gin-gonic/gin"
)

func ConfigRoutes(router *gin.Engine) *gin.Engine {
	main := router.Group("go")
	main.Use(cors.New(cors.Config{
		AllowOrigins:     []string{"*"},
		AllowMethods:     []string{"GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"},
		AllowHeaders:     []string{"Origin", "Content-type", "Accept", "Authorization"},
		ExposeHeaders:    []string{"Content-Length"},
		AllowCredentials: true,
		MaxAge:           12 * time.Hour,
	}))
	{
		books := main.Group("books")
		{

			books.GET("/:id", security.VerifyToken(), controllers.ShowBook)
			books.GET("/", security.VerifyToken(), controllers.ShowBooks)
			books.POST("/", security.VerifyToken(), controllers.CreateBook)
			books.PUT("/", security.VerifyToken(), controllers.UpdateBook)
			books.DELETE("/:id", security.VerifyToken(), controllers.DeleteBook)
		}
		users := main.Group("users")
		{
			users.GET("/", security.VerifyToken(), controllers.ShowUsers)
			users.POST("/", security.VerifyToken(), controllers.CreateUser)
		}
	}

	return router
}
