// 4. Растение. Возможности: задать и редактировать информацию о
// растении; получить информацию об имеющихся растениях в
// оранжерее/растениях определенного вида/месте происхождения;
// закупить новый вид растений; произвести полив; задать температуру;
// установить освещение; выкопать определенный вид растений (убрать из оранжереи).
// Добавить специализированные методы для Кустовое растение, Цветковое растение, Комнатное растение.


package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("=== Старт программы: управление оранжереей ===");

        Greenhouse greenhouse = new Greenhouse();

        BushyPlant rose = new BushyPlant("Шиповник", "Rosa canina",
                "Лес", 1.2);
        FloweringPlant tulip = new FloweringPlant("Тюльпан", "Tulipa",
                "Степь", "красный", 14);
        IndoorPlant ficus = new IndoorPlant("Фикус", "Ficus elastica",
                "Тропики", "керамика", 12);

        greenhouse.purchase(rose);
        greenhouse.purchase(tulip);
        greenhouse.purchase(ficus);

        logger.info("--- Информация о растениях ---");
        greenhouse.getAllPlants().forEach(p -> logger.info(p.getInfo()));
        greenhouse.getAllPlants().forEach(p -> logger.info("Уход: {}", p.care()));

        logger.info("--- Полив всех растений ---");
        greenhouse.waterAll();

        logger.info("--- Установка температуры 22°C и освещения 70% ---");
        greenhouse.setTemperatureAll(22.0);
        greenhouse.setLightingAll(70);

        logger.info("--- Специализированные операции ---");
        rose.markNeedsPruning();
        rose.prune();
        tulip.startBlooming();
        ficus.repot(18);

        logger.info("--- Поиск растений из тропиков ---");
        greenhouse.findByOrigin("Тропики")
                .forEach(p -> logger.info("Найдено: {}", p.getName()));

        logger.info("--- Удаление всех тюльпанов ---");
        greenhouse.removeBySpecies("Tulipa");

        logger.info("Итоговое количество растений: {}", greenhouse.size());
        logger.info("=== Завершение программы ===");
    }
}