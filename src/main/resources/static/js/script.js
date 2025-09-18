console.log("script.js")

const numberPlateInput = document.querySelector("input#numberPlate");

numberPlateInput.addEventListener("input", (e) => {
    numberPlateInput.value = numberPlateInput.value.toUpperCase();
})

const deleteButtons = document.getElementsByClassName('delete-car-btn')

for (let i = 0; i < deleteButtons.length; i++) {
    deleteButtons[i].addEventListener("click", function () {
        const id = deleteButtons[i].getAttribute("data-id")
        deleteCar(id)
    })
}

async function deleteCar(element) {
    var confirmDelete = confirm("Opravdu odstranit?");
    if (confirmDelete) {

        const url = `/cars/${element}`;
        try {
            const response = await fetch(url, {method: "DELETE"});
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }

            console.log("AUTO S ID", element, "VYMAZANO")
            window.location.reload()

        } catch (error) {
            console.error(error.message);
        }
    }
}