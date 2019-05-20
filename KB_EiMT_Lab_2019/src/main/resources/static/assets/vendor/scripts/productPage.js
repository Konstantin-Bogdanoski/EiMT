function deleteProduct(form, productID) {
    if (confirm("Are you sure you want to delete the product?")) {
        form.action(productID);
        return true;
    }
    return false;
}