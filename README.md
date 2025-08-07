# 🛒 Backend — Carrito de Compras | Prueba Técnica 2025
Este proyecto resuelve la lógica de un sistema de carrito de compras para una prueba técnica. Permite consultar productos, agregarlos a un carrito, eliminarlos y obtener recomendaciones de compra según presupuesto disponible. Desarrollado en Java 17 + Spring Boot 3, con arquitectura limpia y endpoints REST listos para consumir desde cualquier frontend.
Backend desarrollado en **Java 17 + Spring Boot 3**, expone una API REST para gestión de productos y carrito de compras.

---

## 🚀 ¿Cómo ejecutar?

1. **Clona el repositorio y entra a la carpeta del backend:**

   ```bash
   cd Carrito-de-Compras

Ejecuta el proyecto (requiere Java 17):
./mvnw spring-boot:run

La API estará disponible en:

http://localhost:8083/api

Documentación interactiva (Swagger):
http://localhost:8083/swagger-ui.html

📂 Estructura del proyecto

src/main/java/com.carritobackend.Carrito.de.Compras/
├── Controller/
│     └── CartController.java
├── Model/
│     ├── Cart.java
│     ├── CartItem.java
│     └── Product.java
└── Service/
└── CartService.java

src/main/resources/
├── application.properties
├── static/
└── templates/

🛠️ Funcionalidades principales
Consultar productos disponibles.

Agregar productos al carrito.

Listar productos del carrito.

Eliminar productos del carrito.

Optimización de compras (mejor combinación por presupuesto).

Totalmente documentado en Swagger para pruebas directas.

💡 Notas
No usa base de datos persistente (funciona en memoria para pruebas).

Listo para integrarse con cualquier frontend moderno (React, Angular, Vue, etc).

Arquitectura limpia y modular, ideal para ampliación o pruebas de concepto.


👨‍💻 Autor
Desarrollado por Carlos Iglesias — Agosto 2025


