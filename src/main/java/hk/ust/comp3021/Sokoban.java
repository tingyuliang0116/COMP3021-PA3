package hk.ust.comp3021;

import hk.ust.comp3021.replay.ReplaySokobanGame;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Arrays;

/**
 * The holder of the entry point of the game.
 */
public class Sokoban {

    /**
     * The entry point of the program.
     *
     * @param args The command line args.
     */
    public static void main(@NotNull String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: java -jar Sokoban.jar <repeat> <map_file> <mode> <fps> <action_file> [<action_file> ...]");
            System.exit(1);
            return;
        }
        try {
            final var repeat = Integer.parseInt(args[0]);
            final var mode = switch (args[2]) {
                case "ROUND_ROBIN" -> ReplaySokobanGame.Mode.ROUND_ROBIN;
                case "FREE_RACE" -> ReplaySokobanGame.Mode.FREE_RACE;
                default -> {
                    throw new IllegalArgumentException("Invalid mode: " + args[2]);
                }
            };
            final var fps = Integer.parseInt(args[3]);
            replayGame(repeat, args[1], mode, fps, Arrays.copyOfRange(args, 4, args.length));
        } catch (IOException e) {
            System.out.println("Failed to open files: " + e);
            System.exit(1);
        } catch (InterruptedException e) {
            System.out.println("Interrupted: " + e);
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgument: " + e);
            System.exit(1);
        } catch (RuntimeException e) {
            System.out.println("RuntimeError: " + e);
            System.exit(1);
        }
    }


    /**
     * @param repeat      number of times to repeat the game in parallel
     * @param mapFile     map file
     * @param mode        mode of the game
     * @param fps         rendering fps
     * @param actionFiles action files
     * @throws IOException          if mapFile cannot be load
     * @throws InterruptedException if the game is interrupted
     */
    public static void replayGame(int repeat,
                                  @NotNull String mapFile,
                                  ReplaySokobanGame.Mode mode,
                                  int fps,
                                  @NotNull String[] actionFiles
    ) throws IOException, InterruptedException {
        final var threads = new Thread[repeat];
        for (int i = 0; i < repeat; i++) {
            final var game = SokobanGameFactory.createReplayGame(mapFile, mode, fps, actionFiles);
            final var thread = new Thread(game);
            threads[i] = thread;
        }
        for (final var th :
            threads) {
            th.start();
        }
        for (final var th :
            threads) {
            th.join();
        }
    }
}
