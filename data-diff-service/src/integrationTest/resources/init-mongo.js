try {
  print("CREATING USER")
  db.createUser(
    {
      user: "data_diff_user",
      pwd: "waes_assignment",
      roles: [{ role: "readWrite", db: "data_diff" }]
    }
  );
} catch (error) {
  print(`Failed to create developer db user:\n${error}`);
};