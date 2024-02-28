
describe("Integration Tests", () => {
	
	beforeEach(() => {
		cy.visit("http://localhost:5173");
	});

	it("tests Header component", () => {
		cy.get("#search-title").contains("Items Lister").should("exist");
		cy.get("#searchInput").should("exist");
	});

	
	it("tests Section component", () => {
		cy.get("#add-item-title").contains("Add Items").should("exist");
		cy.get("h1").contains("Items").should("exist");
	});

	
	it("tests MainComponent", () => {
		cy.get("h1").contains("Items").should("exist");
		cy.get("#list-input").type("Naruto");
		cy.get("#add-item").should("exist");
		cy.get("#add-item").click();
	});

	


	
	it("tests if items can be searched", () => {
		cy.get("#list-input").type("Kirti bam bam");
		cy.get("#add-item").click();
		cy.contains("Kirti bam bam").should("exist");
		cy.get("#searchInput").type("Kirti");
		cy.get("#list").should("contain", "Kirti");
	});
});