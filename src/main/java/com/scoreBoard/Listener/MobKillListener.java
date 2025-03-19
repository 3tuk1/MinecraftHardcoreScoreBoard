package com.scoreBoard.Listener;

import com.scoreBoard.Data.ScoreData;
import com.scoreBoard.Data.ScoreDataManage;
import com.scoreBoard.ScoreCalc.ScoreCalculation;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.entity.Player;

public class MobKillListener implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() instanceof Player) {
            Player player = event.getEntity().getKiller();
            if (!SpawnerSpawnListener.isSpawnerSpawn(event.getEntity())) {
                // スコア計算ロジックをここに追加
                String mobName = event.getEntity().getName();
                switch (mobName) {
                    case "Zombie":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "Skeleton":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 15, "Mob");
                        break;
                    case "Creeper":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 20, "Mob");
                        break;
                    case "Spider":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 8, "Mob");
                        break;
                    case "Slime":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 3, "Mob");
                        break;
                    case "Enderman":
                        // スコア計算ロジックをここに追加
                        // エンドのエンダーマンはスコアが低い
                        if(event.getEntity().getWorld().getName().equals("world_the_end")) {
                            ScoreCalculation.addScore(player, 8, "Mob");
                        } else {
                            ScoreCalculation.addScore(player, 25, "Mob");
                        }
                        break;
                    case "Blaze":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 30, "Mob");
                        break;
                    case "Witch":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 25, "Mob");
                        break;
                    case "Guardian":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 15, "Mob");
                        break;
                    case "EnderDragon":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 200,  "Mob");
                        break;
                    case "Wither":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 200, "Mob");
                        break;
                    case "Phantom":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 8, "Mob");
                        break;
                    case "Pillager":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "Vindicator":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 15, "Mob");
                        break;
                    case "Evoker":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "Vex":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "Illusioner":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 25, "Mob");
                        break;
                    case "Husk":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "Stray":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 17, "Mob");
                        break;
                    case "Drowned":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 13, "Mob");
                        break;
                    case "PigZombie":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 3, "Mob");
                        break;
                    case "ZombieVillager":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 10, "Mob");
                        break;
                    case "WanderingTrader":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, -3, "Mob");
                        break;
                    case "Pig":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 5, "Mob");
                        break;
                    case "Sheep":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 5, "Mob");
                        break;
                    case "Cow":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 8, "Mob");
                        break;
                    case "Chicken":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 3, "Mob");
                        break;
                    case "Squid":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 3, "Mob");
                        break;
                    case "Wolf":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, -1, "Mob");
                        break;
                    case "MushroomCow":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, -10, "Mob");
                        break;
                    case "SnowMan":
                        // スコア計算ロジックをここに追加
                        //addScore(player, 0, "Mob");
                        break;
                    case "Ocelot":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, -5, "Mob");
                        break;
                    case "IronGolem":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 30, "Mob");
                        break;
                    case "Horse":
                        // スコア計算ロジックをここに追加
                        //addScore(player, 0, "Mob");
                        break;
                    case "Rabbit":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, 3, "Mob");
                        break;
                    case "Villager":
                        // スコア計算ロジックをここに追加
                        ScoreCalculation.addScore(player, -5, "Mob");
                        break;
                    default:
                        break;
                }
            }
        }
    }

}