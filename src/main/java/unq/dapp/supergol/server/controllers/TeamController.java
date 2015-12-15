package unq.dapp.supergol.server.controllers;

import unq.dapp.supergol.model.League;
import unq.dapp.supergol.model.Team;
import unq.dapp.supergol.model.repositories.Repository;

import java.util.stream.Collectors;

import static spark.Spark.get;

public class TeamController extends CRUDController<Team> {
  private final Repository<League> leagueRepository;

  public TeamController(Class<Team> clazz, Repository<Team> repository, Repository<League> leagueRepository) {
    super(clazz, repository);
    this.leagueRepository = leagueRepository;
  }

  @Override
  public void registerRoutes(String baseUrl) {
    super.registerRoutes(baseUrl);

    get(baseUrl + "/:id/results", (request, response) -> {
      Team team = getEntityFromRequest(request);

      return leagueRepository.all().stream()
        .map(it -> new Result(it, it.scoreFor(team)))
        .collect(Collectors.toList());
    }, transformer);
  }

  private class Result {
    private final League league;
    private final int score;

    public Result(League league, int score) {
      this.league = league;
      this.score = score;
    }
  }
}
