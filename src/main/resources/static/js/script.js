window.addEventListener("DOMContentLoaded", () => {
  const popup = document.getElementById("popupBienvenida");
  popup.classList.add("show");
});

function cerrarPopup() {
  const popup = document.getElementById("popupBienvenida");
  popup.classList.remove("show");
}
