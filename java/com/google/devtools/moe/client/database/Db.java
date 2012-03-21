// Copyright 2011 The MOE Authors All Rights Reserved.

package com.google.devtools.moe.client.database;

import com.google.devtools.moe.client.repositories.Revision;

import java.util.Set;

/**
 * An abstraction of MOE's database
 *
 */
public interface Db {

  /**
   * @param equivalence  the Equivalence to add to the database
   */
  public void noteEquivalence(Equivalence equivalence);

  /**
   *  @param revision  the Revision to find equivalent revisions for. Two Revisions are equivalent
   *                   when there is an Equivalence containing them in the database.
   *  @param otherRepository  the Repository to find equivalent revisions in
   *
   *  @return all Revisions in otherRepository (have repositoryName of otherRepository) in some
   *          Equivalence with revision, or an empty set if none.
   */
  public Set<Revision> findEquivalences(Revision revision, String otherRepository);

  /**
   * Migrations are stored along with Equivalences to give full historical information for runs of
   * MOE, as not all migrations result in an Equivalence.
   *
   * @param migration  the SubmittedMigration to add to the database
   */
  public void noteMigration(SubmittedMigration migration);

  /**
   * @param dbLocation  the location to write the contents of this Db to
   */
  public void writeToLocation(String dbLocation);
}
