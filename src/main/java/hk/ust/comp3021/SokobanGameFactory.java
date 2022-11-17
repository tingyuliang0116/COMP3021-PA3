package hk.ust.comp3021;

import hk.ust.comp3021.game.GameMap;
import hk.ust.comp3021.game.GameState;
import hk.ust.comp3021.game.SokobanGame;
import hk.ust.comp3021.replay.ReplaySokobanGame;
import hk.ust.comp3021.replay.StreamInputEngine;
import hk.ust.comp3021.replay.TerminalRenderingEngine;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Factory for creating Sokoban games
 */
public class SokobanGameFactory {

    /**
     * Create a Sokoban game.
     *
     * @param mapFile     Map file.
     * @param mode        Mode of the game.
     * @param frameRate   Rendering frameRate.
     * @param actionFiles Action files.
     * @return The Sokoban game.
     * @throws IOException if mapFile cannot be load
     */
    public static @NotNull SokobanGame createReplayGame(@NotNull String mapFile,
                                                        @NotNull ReplaySokobanGame.Mode mode,
                                                        int frameRate,
                                                        @NotNull String[] actionFiles
    ) throws IOException {
        Path file = Path.of(mapFile);
        final var gameMap = loadGameMap(file);
        final var inputEngines = Arrays.stream(actionFiles).map(f -> {
            try {
                return new StreamInputEngine(new FileInputStream(f));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return new ReplaySokobanGame(
                mode,
                frameRate,
                new GameState(gameMap),
                inputEngines,
                new TerminalRenderingEngine(System.out)
        );
    }

    /**
     * @param mapFile The file containing the game map.
     * @return The parsed game map.
     * @throws IOException When there is an issue loading the file.
     */
    public static @NotNull GameMap loadGameMap(@NotNull Path mapFile) throws IOException {
        final var fileContent = Files.readString(mapFile);
        return GameMap.parse(fileContent);
    }

}
