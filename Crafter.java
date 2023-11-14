public class Crafter {

    public ResultItem craftShapeless(String[][] craftGrid, Recipe recipe, String[] ingredients) {
        for (CraftItem item : recipe.items) {
            boolean found = false;
            for (int x = 0; x < ingredients.length; x++) {
                if (item.name.equals(ingredients[x])) {
                    // remove item from remaining ingredients
                    ingredients[x] = null;
                    found = true;
                    break;
                }
            }
            if (!found) {
                // required item not in grid failed craft
                return null;
            }
        }
        // successful craft, found all items
        return recipe.resultItem;
    }

    public ResultItem craftShapedInit(String[][] craftGrid, Recipe recipe) {
        int maxX = 0, maxY = 0, width, height;
        for (CraftItem item : recipe.items) {
            maxX = Math.max(maxX, item.posX);
            maxY = Math.max(maxY, item.posY);
        }
        width = 1 + maxX;
        height = 1 + maxY;

        String[][] newGrid = new String[width][height];
        ResultItem result;
        for (int x = 0; x < 1 + (craftGrid.length - width); x++) {
            for (int y = 0; y < 1 + (craftGrid[x].length - height); y++) {
                copyGrid(craftGrid, newGrid, x, y);
                result = craftShaped(newGrid, recipe);
                if (result != null)
                    return result;
            }
        }
        return null;
    }

    public void copyGrid(String[][] fromGrid, String[][] toGrid, int startX, int startY) {
        for (int x = 0; x < toGrid.length; x++) {
            for (int y = 0; y < toGrid[x].length; y++) {
                toGrid[x][y] = fromGrid[x + startX][y + startY];
            }
        }
    }

    public ResultItem craftShaped(String[][] craftGrid, Recipe recipe) {
        for (CraftItem item : recipe.items) {
            if (item.name.equals(craftGrid[item.posX][item.posY])) {
                // item is in grid in correct spot
                continue;
            } else {
                // required item not in grid failed craft
                return null;
            }
        }
        // successful craft, found all items
        return recipe.resultItem;
    }

    public ResultItem craft(String[][] craftGrid, Recipe recipe) {
        int ingredientCount = 0;
        String[] ingredients = new String[9];

        for (int x = 0; x < craftGrid.length; x++) {
            for (int y = 0; y < craftGrid[x].length; y++) {
                if (craftGrid[x][y] != null)
                    ingredients[ingredientCount++] = craftGrid[x][y];
            }
        }
        if (ingredientCount != recipe.items.length)
            return null; // failed craft

        if (recipe.isShaped)
            return craftShapedInit(craftGrid, recipe);
        else
            return craftShapeless(craftGrid, recipe, ingredients);
    }

    public void printGrid(String[][] grid) {
        for(String[] row : grid) {
            for (String item : row) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
    }
}

class Recipe {

    final boolean isShaped;
    final CraftItem[] items;
    final ResultItem resultItem;

    public Recipe(ResultItem resultItem, boolean isShaped, CraftItem[] items) {
        this.isShaped = isShaped;
        this.items = items;
        this.resultItem = resultItem;
    }
}

class CraftItem {
    final String name;
    final int posX, posY;

    public CraftItem(String name, int posX, int posY) {
        this.name = name;
        this.posX = posX;
        this.posY = posY;
    }
}

class ResultItem {
    final String name;
    final int count;

    public ResultItem(String name, int count) {
        this.name = name;
        this.count = count;
    }
}