package com.elite.run.bootstrap;

import com.elite.run.model.RunSubmission;
import com.elite.run.repository.RunSubmissionRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RunDataSeeder implements CommandLineRunner {

    private final RunSubmissionRepository runSubmissionRepository;
    private final Random random = new Random(42L);

    public RunDataSeeder(RunSubmissionRepository runSubmissionRepository) {
        this.runSubmissionRepository = runSubmissionRepository;
    }

    @Override
    public void run(String... args) {
        if (runSubmissionRepository.count() > 0) {
            return;
        }

        List<String> players = List.of(
                "alex", "riven", "milo", "casey", "hexa", "nova", "taro", "lucas",
                "sable", "dante", "ryan", "kira", "feron", "jules", "atlas", "vex",
                "meira", "zane", "preston", "lyra", "noah", "sora", "ivory", "onyx",
                "kevin", "nina", "silas", "orion", "mira", "brian", "tess", "cyrus",
                "clara", "adrian", "selene", "mason", "hazel", "oliver", "layla",
                "ethan", "amelia", "max", "zoe", "liam", "sophie", "harper", "leo",
                "mia", "jack", "ella", "nora", "felix", "ava", "noel", "rhea", "marin",
                "jasper", "valen", "cora", "dorian", "gwen", "ian", "juno", "tobin",
                "quinn", "emily", "hugo", "aurora", "matt", "sienna", "leon", "fiona",
                "dylan", "vera", "caleb", "piper", "ryder", "teo", "sara", "gavin",
                "nadia", "kellan", "brook", "hannah", "beck", "aria", "owen", "charlie",
                "jordan", "mila", "nathan", "riley", "luka", "zoey", "elijah", "paula",
                "morgan", "alina", "theo", "lena", "frank", "yara", "jonah", "grace",
                "aidan", "wendy", "sam", "naomi", "reed", "margo", "vincent", "isla",
                "harlan", "maia", "damon", "ruth", "tim", "lola", "cole", "sadie",
                "brent", "jess", "marco", "stella", "eric", "flora", "cian", "dahlia",
                "drew", "talia", "sean", "jasmine", "peter", "luna", "jude", "ivy",
                "gareth", "alexis", "louis", "carmen", "oscar", "vivian", "mike", "prue"
        );

        List<String> games = List.of("Apex Run", "Nightfall");
        List<String> apexStages = List.of("Harbor Run", "Glass Gate", "Mercury Lift", "Drift Line", "Red Signal");
        List<String> nightfallStages = List.of("Glass Gate", "Stone Span", "Hush Port", "Sever Line", "Blue Vale");
        List<String> categories = List.of("000", "001", "002", "003", "004", "005", "006", "007");

        List<RunSubmission> submissions = new java.util.ArrayList<>();

        for (int i = 0; i < 220; i++) {
            String player = players.get(i % players.size());
            if (i % 9 == 0) {
                player = players.get((i * 7 + 13) % players.size());
            }
            String game = games.get(i % games.size());
            List<String> stages = game.equals("Apex Run") ? apexStages : nightfallStages;
            String stageName = stages.get((i * 3 + 1) % stages.size());
            String category = categories.get((i * 3 + 1) % categories.size());
            String difficulty = switch (category) {
                case "000", "001" -> "Sprint";
                case "002", "003", "004" -> "Standard";
                default -> "Endurance";
            };

            long lowerBound = 50000L;
            long upperBound = 125000L;
            if (game.equals("Nightfall")) {
                lowerBound = 56000L;
                upperBound = 135000L;
            }

            long timeMs = lowerBound + random.nextLong(upperBound - lowerBound);
            if (i % 11 == 0) {
                timeMs = Math.min(timeMs + 18000L, upperBound + 16000L);
            }

            submissions.add(new RunSubmission(player, game, stageName, category, difficulty, timeMs));
        }

        List<RunSubmission> ordered = submissions.stream()
                .sorted((a, b) -> Long.compare(a.getTimeMs(), b.getTimeMs()))
                .toList();

        runSubmissionRepository.saveAll(ordered);
    }
}
