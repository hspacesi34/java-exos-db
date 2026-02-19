import com.projet.database.Mysql;
import com.projet.entity.Category;
import com.projet.entity.Game;
import com.projet.entity.Manufacturer;
import com.projet.repository.CategoryRepository;
import com.projet.repository.GameRepository;
import com.projet.repository.ManufacturerRepository;

public class Main {

    public static void main() {

    }

    public static void createCategory() {
        CategoryRepository categoryRepository = new CategoryRepository();
        Category category = new Category("4X");
        System.out.println(categoryRepository.save(category));
    }

    public static void getCategory() {
        CategoryRepository categoryRepository = new CategoryRepository();
        System.out.println(categoryRepository.find(1));
    }

    public static void getAllCategories() {
        CategoryRepository categoryRepository = new CategoryRepository();
        System.out.println(categoryRepository.findAll());
    }

    public static void createManufacturer() {
        ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
        Manufacturer manufacturer = new Manufacturer("Gearbox");
        System.out.println(manufacturerRepository.save(manufacturer));
    }

    public static void getManufacturer() {
        ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
        System.out.println(manufacturerRepository.find(2));
    }

    public static void getAllManufacturers() {
        ManufacturerRepository manufacturerRepository = new ManufacturerRepository();
        System.out.println(manufacturerRepository.findAll());
    }

    public static void createGame() {
        GameRepository gameRepository = new GameRepository();
        Game game = new Game("Borderlands", "Shooter looter loufoque");
        game.setManufacturer(new Manufacturer(4, "Gearbox"));
        game.addCategory(new Category(1, "FPS"));
        game.addCategory(new Category(2, "RPG"));
        System.out.println(gameRepository.save(game));
    }

    public static void getGame() {
        GameRepository gameRepository = new GameRepository();
        System.out.println(gameRepository.find(4));
    }

    public static void getAllGames() {
        GameRepository gameRepository = new GameRepository();
        System.out.println(gameRepository.findAll());
    }
}
