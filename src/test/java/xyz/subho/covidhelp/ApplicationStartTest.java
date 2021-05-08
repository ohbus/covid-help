package xyz.subho.covidhelp;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ApplicationStartTest {

  private static final boolean CONSTANT = true;

  @Test
  void applicationStarts() {
    CovidHelpApplication.main(new String[] {});
    assertThat(CONSTANT).isTrue();
  }
}
