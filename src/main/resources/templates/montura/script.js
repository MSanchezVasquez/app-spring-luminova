document.addEventListener("DOMContentLoaded", function () {
  // Función para agregar una montura a la grilla
  function agregarMonturaGrilla(montura) {
    const grilla = document.getElementById("grilla-monturas");

    const divProducto = document.createElement("div");
    divProducto.classList.add("producto");
    divProducto.setAttribute("data-id", montura.id);
    divProducto.setAttribute("data-nombre", montura.nombre);
    divProducto.setAttribute("data-precio", montura.precio);

    divProducto.innerHTML = `
       <img src="images/montura1.jpg" alt="${montura.nombre}" />
       <h3>${montura.nombre}</h3>
       <p>S/${montura.precio}</p>
       <button class="btn-agregar-carrito">Agregar al carrito</button>
     `;

    grilla.appendChild(divProducto);
  }

  // Función para cargar todas las monturas desde el backend
  function cargarMonturas() {
    fetch("/montura/listarMonturas")
      .then((response) => response.json())
      .then((monturas) => {
        const grilla = document.getElementById("grilla-monturas");
        grilla.innerHTML = ""; // Limpiar la grilla antes de agregar las nuevas monturas
        monturas.forEach((montura) => agregarMonturaGrilla(montura));
      })
      .catch((error) => console.error("Error al cargar las monturas:", error));
  }

  // Manejar la creación de una nueva montura
  document
    .getElementById("form-creacion-montura")
    .addEventListener("submit", function (event) {
      event.preventDefault(); // Evitar que el formulario se envíe de forma tradicional

      // Obtener los valores del formulario
      const nombre = document.getElementById("nombre").value;
      const categoriaId = document.getElementById("categoria").value;
      const precio = document.getElementById("precio").value;

      // Crear el objeto de la nueva montura
      const nuevaMontura = {
        nombre: nombre,
        categoria: { id: categoriaId }, // Suponiendo que la categoría ya está asociada
        precio: precio,
      };

      // Enviar la nueva montura al servidor
      fetch("/montura/crear", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(nuevaMontura),
      })
        .then((response) => response.json())
        .then((montura) => {
          // Una vez que la montura es creada, agregarla a la grilla
          agregarMonturaGrilla(montura);
        })
        .catch((error) => console.error("Error al crear la montura:", error));

      // Limpiar el formulario
      document.getElementById("form-creacion-montura").reset();
    });

  // Cargar las monturas al iniciar la página
  cargarMonturas();
});
