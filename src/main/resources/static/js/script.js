console.log("script.js")

const numberPlateInput = document.querySelector("input#numberPlate");
const vinInput = document.querySelector("input#vinCode");
// Number plate contains uppercase chars only
if (numberPlateInput != null) {
    numberPlateInput.addEventListener("input", (e) => {
        numberPlateInput.value = numberPlateInput.value.toUpperCase();
    })
}
if (vinInput != null) {
// VIN contains uppercase chars only
    vinInput.addEventListener("input", (e) => {
        vinInput.value = vinInput.value.toUpperCase();
    })
}

async function deleteInsp(recordId) {
    const confirmDelete = confirm("Opravdu odstranit?");
    if (confirmDelete) {
        const url = `/inspections/${recordId}`
        try {
            const response = await fetch(url, {method: "DELETE"});
            if (!response.ok) {
                throw new Error(response.statusText);
            }
            console.log("Prohlidka s ID ", recordId, "vymazana")
            window.location.reload();
        } catch (error) {
            console.error(error.message);
        }
    }
}

async function deleteCar(recordId) {
    var confirmDelete = confirm("Opravdu odstranit?");
    if (confirmDelete) {

        const url = `/cars/${recordId}`;
        try {
            const response = await fetch(url, {method: "DELETE"});
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }
            
            console.log("AUTO S ID", recordId, "VYMAZANO")
            window.location.reload()

        } catch (error) {
            console.error(error.message);
        }
    }
}

const carDeleteBtns = document.getElementsByClassName('delete-car-btn')
if (carDeleteBtns.length > 0) {
    for (let i = 0; i < carDeleteBtns.length; i++) {
        carDeleteBtns[i].addEventListener("click", function () {
            const id = carDeleteBtns[i].getAttribute("data-id")
            deleteCar(id)
        })
    }
}

const inspDeleteBtns = document.getElementsByClassName("delete-insp")
console.log(inspDeleteBtns);
if (inspDeleteBtns.length > 0) {
    for (let i = 0; i < inspDeleteBtns.length; i++) {
        inspDeleteBtns[i].addEventListener("click", function () {
            const id = inspDeleteBtns[i].getAttribute("data-id")
            deleteInsp(id)
        })
    }
}