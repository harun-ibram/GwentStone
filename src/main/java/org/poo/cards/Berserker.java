package org.poo.cards;

import lombok.Getter;
import lombok.Setter;
import org.poo.fileio.CardInput;

@Getter
@Setter
public final class Berserker extends Minion {
    public Berserker(final CardInput card) {
        super(card);
    }
}
