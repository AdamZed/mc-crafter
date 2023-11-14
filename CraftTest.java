public class CraftTest {
    static Crafter crafter = new Crafter();

    final static Recipe WOOD_PLANKS = new Recipe(
            new ResultItem("WoodPlank", 4),
            false, // not shaped
            new CraftItem[] {
                    new CraftItem("Log", 0, 0)
            });

    final static Recipe MUSHROOM_STEW = new Recipe(
            new ResultItem("MushStew", 1),
            false, // not shaped
            new CraftItem[] {
                    new CraftItem("MushRed", 0, 0),
                    new CraftItem("MushBrown", 0, 0),
                    new CraftItem("Bowl", 0, 0)
            });

    final static Recipe TORCHES = new Recipe(
            new ResultItem("Torch", 4),
            true, // is shaped
            new CraftItem[] {
                    new CraftItem("Coal", 0, 0),
                    new CraftItem("Stick", 1, 0)
            });

    final static Recipe FENCE = new Recipe(
            new ResultItem("Fence", 4),
            true, // is shaped
            new CraftItem[] {
                    new CraftItem("WoodPlank", 0, 0),
                    new CraftItem("WoodPlank", 1, 0),
                    new CraftItem("Stick", 0, 1),
                    new CraftItem("Stick", 1, 1),
                    new CraftItem("WoodPlank", 0, 2),
                    new CraftItem("WoodPlank", 1, 2)
            });

    final static Recipe IRON_PANTS = new Recipe(
            new ResultItem("IronPant", 1),
            true, // is shaped
            new CraftItem[] {
                    new CraftItem("Iron", 0, 0),
                    new CraftItem("Iron", 0, 1),
                    new CraftItem("Iron", 0, 2),
                    new CraftItem("Iron", 1, 0),
                    new CraftItem("Iron", 1, 2),
                    new CraftItem("Iron", 2, 0),
                    new CraftItem("Iron", 2, 2),
            });

    public static void main(String[] args) {
        System.out.println("==SHAPELESS TESTS==");
        System.out.println("Test 1 Pass: " + test1());
        System.out.println("Test 2 Pass: " + test2());
        System.out.println("Test 3 Pass: " + test3());
        System.out.println("Test 4 Pass: " + test4());
        System.out.println("Test 5 Pass: " + test5());
        System.out.println("Test 6 Pass: " + test6());
        System.out.println("Test 7 Pass: " + test7());
        System.out.println("==SHAPED TESTS==");
        System.out.println("Test 8 Pass: " + test8());
        System.out.println("Test 9 Pass: " + test9());
        System.out.println("Test 10 Pass: " + test10());
        System.out.println("Test 11 Pass: " + test11());
        System.out.println("Test 12 Pass: " + test12());
        System.out.println("Test 13 Pass: " + test13());
        System.out.println("Test 14 Pass: " + test14());
        System.out.println("Test 15 Pass: " + test15());
    }

    public static boolean test1() {
        String[][] grid = { {
                "Log", null, null
        }, {
                null, null, null
        }, {
                null, null, null
        }
        };

        ResultItem craftPlanks = crafter.craft(grid, WOOD_PLANKS);
        return craftPlanks != null && craftPlanks.name.equals("WoodPlank") && craftPlanks.count == 4;
    }

    public static boolean test2() {
        String[][] grid = { {
                null, null, null
        }, {
                null, "Log", null
        }, {
                null, null, null
        }
        };
        ResultItem craftPlanks = crafter.craft(grid, WOOD_PLANKS);
        return craftPlanks != null && craftPlanks.name.equals("WoodPlank") && craftPlanks.count == 4;
    }

    public static boolean test3() {
        String[][] grid = { {
                null, null, null
        }, {
                null, "Stick", null
        }, {
                null, null, null
        }
        };
        ResultItem craftPlanks = crafter.craft(grid, WOOD_PLANKS);
        return craftPlanks == null;
    }

    public static boolean test4() {
        String[][] grid = { {
                null, null, null
        }, {
                null, "Log", "Log"
        }, {
                null, null, null
        }
        };

        ResultItem craftPlanks = crafter.craft(grid, WOOD_PLANKS);
        return craftPlanks == null;
    }

    public static boolean test5() {
        String[][] grid = { {
                "MushBrown", null, null
        }, {
                null, "MushRed", "Bowl"
        }, {
                null, null, null
        }
        };

        ResultItem craftStew = crafter.craft(grid, MUSHROOM_STEW);
        return craftStew != null && craftStew.name.equals("MushStew");
    }

    public static boolean test6() {
        String[][] grid = { {
                "MushBrown", null, null
        }, {
                "Bowl", "MushRed", "Bowl"
        }, {
                null, null, null
        }
        };

        ResultItem craftStew = crafter.craft(grid, MUSHROOM_STEW);
        return craftStew == null;
    }

    public static boolean test7() {
        String[][] grid = { {
                null, null, null
        }, {
                "Bowl", "Stone", "MushBrown"
        }, {
                null, null, null
        }
        };

        ResultItem craftStew = crafter.craft(grid, MUSHROOM_STEW);
        return craftStew == null;
    }

    public static boolean test8() {
        String[][] grid = { {
                "Coal", null, null
        }, {
                "Stick", null, null
        }, {
                null, null, null
        }
        };

        ResultItem craftTorch = crafter.craft(grid, TORCHES);
        return craftTorch != null && craftTorch.name.equals("Torch");
    }

    public static boolean test9() {
        String[][] grid = { {
                null, null, null
        }, {
                null, "Coal", null
        }, {
                null, "Stick", null
        }
        };

        ResultItem craftTorch = crafter.craft(grid, TORCHES);
        return craftTorch != null && craftTorch.name.equals("Torch");
    }

    public static boolean test10() {
        String[][] grid = { {
                null, "Coal", null
        }, {
                null, null, null
        }, {
                null, "Stick", null
        }
        };

        ResultItem craftTorch = crafter.craft(grid, TORCHES);
        return craftTorch == null;
    }

    public static boolean test11() {
        String[][] grid = { {
                "WoodPlank", "WoodPlank", "WoodPlank"
        }, {
                "Stick", "Stick", "WoodPlank"
        }, {
                null, null, null
        }
        };

        ResultItem craftFence = crafter.craft(grid, FENCE);
        return craftFence == null;
    }

    public static boolean test12() {
        String[][] grid = { {
                "WoodPlank", "Stick", "WoodPlank"
        }, {
                "WoodPlank", "Stick", "WoodPlank"
        }, {
                null, null, null
        }
        };

        ResultItem craftFence = crafter.craft(grid, FENCE);
        return craftFence != null && craftFence.name.equals("Fence");
    }

    public static boolean test13() {
        String[][] grid = { {
                null, null, null
        }, {
                "WoodPlank", "Stick", "WoodPlank"
        }, {
                "WoodPlank", "Stick", "WoodPlank"
        }
        };

        ResultItem craftFence = crafter.craft(grid, FENCE);
        return craftFence != null && craftFence.name.equals("Fence");
    }

    public static boolean test14() {
        String[][] grid = { {
                "Iron", "Iron", "Iron"
        }, {
                "Iron", null, "Iron"
        }, {
                "Iron", null, "Iron"
        }
        };

        ResultItem craftPants = crafter.craft(grid, IRON_PANTS);
        return craftPants != null && craftPants.name.equals("IronPant");
    }

    public static boolean test15() {
        String[][] grid = { {
                "Iron", "Iron", "Iron"
        }, {
                "Iron", "Iron", "Iron"
        }, {
                "Iron", null, "Iron"
        }
        };

        ResultItem craftPants = crafter.craft(grid, IRON_PANTS);
        return craftPants == null;
    }
}