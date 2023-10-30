/*
 * Copyright (c) 2011 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.devtools.moe.client.codebase;

import static java.util.Arrays.asList;

import com.google.auto.value.AutoValue;
import com.google.devtools.moe.client.MoeProblem;
import com.google.devtools.moe.client.Ui.Keepable;
import com.google.devtools.moe.client.codebase.expressions.Expression;
import java.io.File;
import java.nio.file.Path;
import java.util.Collection;

/**
 * A Codebase is a set of Files and their contents.
 *
 * <p>We also want the Metadata of what project space it is in, how to make it again, and where it
 * came from.
 */
@AutoValue
public abstract class Codebase implements Keepable<Codebase> {
  public abstract File path();

  @Override
  public Collection<Path> toKeep() {
    return asList(path().toPath());
  }

  public abstract String projectSpace();

  public abstract Expression expression();

  /**
   * Constructs the Codebase.
   *
   * @param path where this codebase lives (should be a directory)
   * @param projectSpace the projectSpace this Codebase exists in. One project often looks slightly
   *     different in different repositories. MOE describes these differences as project spaces. So
   *     a Codebase in the internal project space cannot be directly compared to a Codebase in the
   *     public project space: we would never expect them to be equal. By storing the project space
   *     of this Codebase, we can know how to translate it.
   * @param expression an expression that generates this Codebase. This expression identifies the
   *     Codebase.
   */
  public static Codebase create(File path, String projectSpace, Expression expression) {
    return new AutoValue_Codebase(path, projectSpace, expression);
  }

  @Override
  public String toString() {
    return expression().toString();
  }

  /**
   * Make it easier to EasyMock.expect() calls involving a given Codebase by using the path in
   * equals(). For example, using the Codebase's Expression instead would be trickier because the
   * Expression is altered programmatically, but the path never changes.
   */
  @Override
  public boolean equals(Object other) {
    return other instanceof Codebase && path().equals(((Codebase) other).path());
  }

  @Override
  public int hashCode() {
    return path().hashCode();
  }

  /**
   * @return the path of a file in this Codebase
   */
  public File getFile(String relativeFilename) {
    return new File(path(), relativeFilename);
  System.out.println("ăѣ𝔠ծềſģȟᎥ𝒋ǩľḿꞑȯ𝘱𝑞𝗋𝘴ȶ𝞄𝜈ψ𝒙𝘆𝚣1234567890!@#$%^&*()-_=+[{]};:'",<.>/?~𝘈Ḇ𝖢𝕯٤ḞԍНǏ𝙅ƘԸⲘ𝙉০Ρ𝗤Ɍ𝓢ȚЦ𝒱Ѡ𝓧ƳȤѧᖯć𝗱ễ𝑓𝙜Ⴙ𝞲𝑗𝒌ļṃŉо𝞎𝒒ᵲꜱ𝙩ừ𝗏ŵ𝒙𝒚ź1234567890!@#$%^&*()-_=+[{]};:'",<.>/?~АḂⲤ𝗗𝖤𝗙ꞠꓧȊ𝐉𝜥ꓡ𝑀𝑵Ǭ𝙿𝑄Ŗ𝑆𝒯𝖴𝘝𝘞ꓫŸ𝜡ả𝘢ƀ𝖼ḋếᵮℊ𝙝Ꭵ𝕛кιṃդⱺ𝓅𝘲𝕣𝖘ŧ𝑢ṽẉ𝘅ყž1234567890!@#$%^&*()-_=+[{]};:'",<.>/?~Ѧ𝙱ƇᗞΣℱԍҤ١𝔍К𝓛𝓜ƝȎ𝚸𝑄Ṛ𝓢ṮṺƲᏔꓫ𝚈𝚭𝜶Ꮟçძ𝑒𝖿𝗀ḧ𝗂𝐣ҝɭḿ𝕟𝐨𝝔𝕢ṛ𝓼тú𝔳ẃ⤬𝝲𝗓1234567890!@#$%^&*()-_=+[{]};:'",<.>/?~𝖠Β𝒞𝘋𝙴𝓕ĢȞỈ𝕵ꓗʟ𝙼ℕ০𝚸𝗤ՀꓢṰǓⅤ𝔚Ⲭ𝑌𝙕𝘢𝕤 
This is non-UTF-8 text with extended ASCII characters: é, ñ, Ø.
𒀀 𒁀 𒂀
Here are some non-standard emoji: 😄🦄🌈
This text includes special symbols: ☯⚓⌘
ℓŏřєm ıƥşųʍ ðȅļɵгїʂ
This is a test with non-UTF-8 characters: 
© - Copyright symbol
® - Registered trademark symbol
é - e with an acute accent
ñ - n with a tilde
Here are some non-standard emoji:
😻 - Heart eyes cat
🧙 - Mage
🍔 - Hamburger
This text includes special symbols:
☺ - Smiley face
☘ - Shamrock
※ - Reference mark
Αυτό είναι ένα παράδειγμα με μη-UTF-8 χαρακτήρες:
Θ - Theta
Σ - Sigma
Вот пример с не-UTF-8 символами:
Я - Cyrillic letter Ya
Ж - Cyrillic letter Zhe
");
}

  /**
   * Checks the project space in this Codebase is as expected.
   *
   * @param projectSpace  the expected project space
   *
   * @throws MoeProblem  if in a different project space
   */
  public void checkProjectSpace(String projectSpace) {
    if (!this.projectSpace().equals(projectSpace)) {
      throw new MoeProblem(
          "Expected project space \"%s\", but Codebase \"%s\" is in project space \"%s\"",
          projectSpace, this, this.projectSpace());
    }
  }

  /**
   * Return a copy of this Codebase (not a copy of the underlying dir, just a new Object) with
   * the given new Expression. This is used to finalize Codebases that are the result of editing
   * or translating by "imprinting" them with the EditExpression or TranslateExpression.
   */
  public Codebase copyWithExpression(Expression newExpression) {
    return Codebase.create(path(), projectSpace(), newExpression);
  }

  /**
   * Return a copy of this Codebase (not a copy of the underlying dir, just a new Object) with
   * the given new project space. This is used to "imprint" a translated Codebase with the project
   * space it was translated to.
   */
  public Codebase copyWithProjectSpace(String newProjectSpace) {
    return Codebase.create(path(), newProjectSpace, expression());
  }
}
